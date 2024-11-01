DROP TABLE chamcong
/

DROP TABLE nhanvien_kynang
/

DROP TABLE kynang
/

DROP TABLE taikhoan
/

DROP TABLE hopdong
/

ALTER TABLE nhanvien DROP CONSTRAINT nhanvien_fk
/

ALTER TABLE phongban DROP CONSTRAINT phongban_fk
/

DROP TABLE phongban
/

DROP TABLE yeucau
/

DROP TABLE nhanvien
/

ALTER SESSION SET nls_date_format = 'YYYY-MM-DD';

set feedback ON
set serveroutput on;
/
drop sequence my_sequence_hopdong;
/
drop sequence my_sequence_kynang;
/
drop sequence my_sequence_nhanvien;
/
drop sequence my_sequence_nhanvien_kynang;
/
drop sequence my_sequence_phongban;
/
drop sequence my_sequence_taikhoan;
/
drop sequence my_sequence_yeucau;
/

create table phongban (
mapb            number(4),
tenpb           nvarchar2(50) not null,
ngthanhlap      date not null,
matruongphong   number(4),
ngaynhanchuc    date,
constraint phongban_pk primary key (mapb),
constraint taikhoan_tenpb_uk unique (tenpb)
);

create table nhanvien (
MANV            number(4),
HOTEN           Nvarchar2(50) not null,
GIOITINH        Nvarchar2(3) not null,
NGSINH          date not null,
SDT             varchar(15) not null,
EMAIL           varchar(50) not null,
DIACHI          Nvarchar2(150),
CCCD            varchar(12) not null,
CAPBAC          varchar(10) not null,
MAPB            number(4) not null,
constraint nhanvien_pk primary key (MANV),
constraint nhanvien_fk foreign key (MAPB) references PHONGBAN (MAPB),
constraint nhanvien_sdt_uk unique (SDT),
constraint nhanvien_email_uk unique (EMAIL),
constraint nhanvien_cccd_uk unique (CCCD),
constraint nhanvien_gioitinh_ck check (gioitinh in ('Nam', N'Nữ'))
);
/

create table hopdong (
mahd            number(4),
manv            number(4) not null,
ngaybdhd        date not null,
ngaykthd        date,
constraint hopdong_pk primary key (mahd),
constraint hopdong_fk foreign key (manv) references NHANVIEN (manv),
constraint hopdong_manv_uk unique (manv)
);
/
ALTER TABLE PHONGBAN ADD CONSTRAINT phongban_fk foreign key (matruongphong) references NHANVIEN (manv);

create table taikhoan (
matk            number(4),
manv            number(4),
tentk           nvarchar2(50) not null,
matkhau         nvarchar2(50) not null,
loaitaikhoan    nvarchar2(10) not null,
constraint taikhoan_pk primary key (matk),
constraint taikhoan_fk foreign key (manv) references NHANVIEN (manv) ,
constraint taikhoan_tentk_uk unique (tentk),
constraint taikhoan_loaitk_ck check (loaitaikhoan in (N'quản lý', N'nhân viên'))
);
/

create table kynang (
makn            number(4),
tenkn           nvarchar2(20) not null,
constraint kynang_pk primary key (makn),
constraint kynang_ck check (tenkn in (N'tin học', N'tiếng anh')),
constraint tenkn_uk unique (tenkn)
);
/

create table nhanvien_kynang (
makn            number(4),
manv            number(4),
capbac          char(3) not null,
constraint nhanvien_kynang_pk primary key (makn, manv),
constraint nhanvien_kynang_fk_kn foreign key (makn) references KYNANG (makn),
constraint nhanvien_kynang_fk_nv foreign key (manv) references NHANVIEN (manv),
constraint nhanvien_kynang_capbac_ck check (capbac in ('A', 'B', 'C', 'A1', 'A2', 'B1', 'B2' , 'C1' , 'C2'))
);
/

create table chamcong (
manv            number(4) not null,
thanglamviec    number(2) not null,
songaylamviec   number(2) not null,
songaynghi      number(2) not null,
sogiotangca     number(2) not null,
songayditre     number(2) not null,
constraint chamcong_pk primary key (manv,thanglamviec),
constraint chamcong_fk foreign key (manv) references NHANVIEN (manv),
constraint chamcong_thanglamviec_ck check (thanglamviec >=1 and thanglamviec <= 12),
constraint chamcong_songaylamviec_ck check (songaylamviec >= 0 and songaylamviec <= 31),
constraint chamcong_songaynghi_ck check (songaynghi >= 0 and songaynghi <= 31),
constraint chamcong_sogiotangca_ck check (sogiotangca >= 0 and sogiotangca <= 48)
);

CREATE TABLE YEUCAU (
mayc number(4),
manv number(4),
noidung nvarchar2(500),
trangthai number(1),
constraint yeucau_pk primary key (mayc),
constraint yeucau_fk foreign key (manv) references NHANVIEN (manv),
constraint yeucau_ck check (trangthai IN (0,1))
);

/
CREATE SEQUENCE my_sequence_hopdong
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_kynang
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_nhanvien
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_nhanvien_kynang
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_phongban
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_taikhoan
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE my_sequence_yeucau
START WITH 1
INCREMENT BY 1;
/





--Trigger M?i nhân viên không có quá 10 k? n?ng
CREATE OR REPLACE TRIGGER TRG_NV_KN
BEFORE INSERT ON NHANVIEN_KYNANG
FOR EACH ROW
DECLARE count_skill NUMBER;
BEGIN
    SELECT COUNT (*)
    INTO count_skill
    FROM NHANVIEN_KYNANG 
    WHERE MANV = :NEW.MANV;
    
    IF(count_skill >= 9) THEN
    DBMS_OUTPUT.PUT_LINE('M?i nhân viên không có quá 9 k? n?ng');
    ELSE DBMS_OUTPUT.PUT_LINE('Thêm k? n?ng thành công');
    END IF;
END;
/
--S? ng  y ?i l  m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng  y: không ???c quá 30 ng  y
-- N?u tháng có 31 ng  y: không ???c quá 31 ng  y
-- N?u tháng 2: không ???c quá 29 ng  y
CREATE OR REPLACE TRIGGER TG_INSERT_CHAMCONG_1
BEFORE INSERT ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC > 30 OR :NEW.SONGAYDITRE > 30 OR :NEW.SONGAYNGHI > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC > 31 OR :NEW.SONGAYDITRE > 31 OR :NEW.SONGAYNGHI > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC = 2 AND (:NEW.SONGAYLAMVIEC > 29 OR :NEW.SONGAYDITRE > 29 OR :NEW.SONGAYNGHI > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/
--S? ng  y ?i l  m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng  y: không ???c quá 30 ng  y
-- N?u tháng có 31 ng  y: không ???c quá 31 ng  y
-- N?u tháng 2: không ???c quá 29 ng  y
CREATE OR REPLACE TRIGGER TG_UPDATE_CHAMCONG_1
BEFORE UPDATE ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC > 30 OR :NEW.SONGAYDITRE > 30 OR :NEW.SONGAYNGHI > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC > 31 OR :NEW.SONGAYDITRE > 31 OR :NEW.SONGAYNGHI > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC = 2 AND (:NEW.SONGAYLAMVIEC > 29 OR :NEW.SONGAYDITRE > 29 OR :NEW.SONGAYNGHI > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/
    
--Thu?c tính NGSINH c?a NHANVIEN ph?i l?n h?n 18 tu?i
CREATE OR REPLACE TRIGGER TG_INSERT_NHANVIEN_1
BEFORE INSERT ON NHANVIEN
    FOR EACH ROW
    BEGIN
        IF(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :NEW.NGSINH) <= 18) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/      
CREATE OR REPLACE TRIGGER TG_UPDATE_NHANVIEN_1
BEFORE UPDATE ON NHANVIEN
    FOR EACH ROW
    BEGIN
        IF(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :NEW.NGSINH) <= 18) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/   
CREATE OR REPLACE TRIGGER TG_INSERT_PHONGBAN_1
BEFORE INSERT ON PHONGBAN
    FOR EACH ROW
    BEGIN
        IF(:NEW.NGTHANHLAP > SYSDATE) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/        
CREATE OR REPLACE TRIGGER TG_UPDATE_PHONGBAN_1
BEFORE UPDATE ON PHONGBAN
    FOR EACH ROW
    BEGIN
        IF(:NEW.NGTHANHLAP > SYSDATE) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/        

  
            


--trigger m?i phòng ban có t?i ?a 200 nhân viên
CREATE OR REPLACE TRIGGER TRG_NV_TOIDA
BEFORE INSERT ON NHANVIEN
FOR EACH ROW
DECLARE
    v_nhanvien_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_nhanvien_count
    FROM NHANVIEN
    WHERE MAPB = :NEW.MAPB;

    IF v_nhanvien_count >= 200 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Phòng ban nay da dat so luong toi da.');
    END IF;
END;
/
--S? ng  y ?i l  m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng  y: không ???c quá 30 ng  y
-- N?u tháng có 31 ng  y: không ???c quá 31 ng  y
-- N?u tháng 2: không ???c quá 29 ng  y
CREATE OR REPLACE TRIGGER TG_INSERT_CHAMCONG_2
BEFORE INSERT ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (2) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');     
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/
--S? ng  y ?i l  m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng  y: không ???c quá 30 ng  y
-- N?u tháng có 31 ng  y: không ???c quá 31 ng  y
-- N?u tháng 2: không ???c quá 29 ng  y
CREATE OR REPLACE TRIGGER TG_UPDATE_CHAMCONG_2
BEFORE UPDATE ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (2) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE  > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');     
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/


--ngày b?t ?àu h?p ??ng nhân viên ph?i ?? 18 tu?i
CREATE OR REPLACE TRIGGER TG_INSERT_HOPDONG_1
BEFORE INSERT ON HOPDONG
    FOR EACH ROW
    DECLARE v_ngsinh DATE;
    BEGIN
        SELECT NGSINH INTO v_ngsinh
        FROM NHANVIEN
        WHERE MANV = :NEW.MANV;
        IF(TRUNC(MONTHS_BETWEEN(:NEW.NGAYBDHD, v_ngsinh) / 12) < 18 OR :NEW.NGAYBDHD > :NEW.NGAYKTHD) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/        
CREATE OR REPLACE TRIGGER TG_UPDATE_HOPDONG_1
BEFORE UPDATE ON HOPDONG
    FOR EACH ROW
    DECLARE v_ngsinh DATE;
    BEGIN
        SELECT NGSINH INTO v_ngsinh
        FROM NHANVIEN
        WHERE :NEW.MANV = MANV;
        IF(TRUNC(MONTHS_BETWEEN(:NEW.NGAYBDHD, v_ngsinh) / 12) < 18 OR :NEW.NGAYBDHD > :NEW.NGAYKTHD) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/
create or replace NONEDITIONABLE procedure     SLEEP(n number) is
begin
  dbms_lock.sleep(n);
end;
/
CREATE OR REPLACE PROCEDURE totalSalary (
    p_MANV IN CHAMCONG.MANV%TYPE,
    p_THANGLAMVIEC IN CHAMCONG.THANGLAMVIEC%TYPE,
    p_TongLuong OUT NUMBER
) IS
    v_SoNgayLamViec CHAMCONG.SONGAYLAMVIEC%TYPE;
    v_SoNgayNghi CHAMCONG.SONGAYNGHI%TYPE;
    v_SoGioTangCa CHAMCONG.SOGIOTANGCA%TYPE;
    v_SoNgayDiTre CHAMCONG.SONGAYDITRE%TYPE;
    v_CapBac NHANVIEN.CAPBAC%TYPE;
    v_HeSoLuong NUMBER;
BEGIN
    -- Lấy thông tin từ bảng CHAMCONG
    SELECT SONGAYLAMVIEC, SONGAYNGHI, SOGIOTANGCA, SONGAYDITRE
    INTO v_SoNgayLamViec, v_SoNgayNghi, v_SoGioTangCa, v_SoNgayDiTre
    FROM CHAMCONG
    WHERE MANV = p_MANV AND THANGLAMVIEC = p_THANGLAMVIEC;

    -- Lấy thông tin từ bảng NHANVIEN
    SELECT CAPBAC
    INTO v_CapBac
    FROM NHANVIEN
    WHERE MANV = p_MANV;

    -- Xác định hệ số lương dựa trên cấp bậc
    IF v_CapBac = 'Leader' THEN
        v_HeSoLuong := 1.75;
    ELSIF v_CapBac = 'Senior' THEN
        v_HeSoLuong := 1.5;
    ELSIF v_CapBac = 'Junior' THEN
        v_HeSoLuong := 1.3;
    ELSE
        v_HeSoLuong := 1.0;
    END IF;

    -- Tính tổng lương
    p_TongLuong := (v_SoNgayLamViec * 300000 * v_HeSoLuong) +
                   (v_SoNgayDiTre * 0.85 * 100000 * v_HeSoLuong) +
                   (v_SoGioTangCa * 50000 * v_HeSoLuong);

    -- Nếu có ngày đi trễ, trừ 15% lương của ngày hôm đó
    IF v_SoNgayDiTre >= 1 THEN
        p_TongLuong := p_TongLuong - (v_SoNgayDiTre * 0.15 * 300000 * v_HeSoLuong);
    ELSIF v_SoNgayDiTre >= 4 THEN
        p_TongLuong := p_TongLuong * 0.85;
    END IF;
END;
/
CREATE OR REPLACE PROCEDURE update_capbac AS
BEGIN
    -- Update CAPBAC to Junior
    UPDATE NHANVIEN n
    SET n.CAPBAC = 'Junior'
    WHERE n.CAPBAC = 'Fresher'
    AND EXISTS (
        SELECT *
        FROM HOPDONG h
        WHERE h.MANV = n.MANV
        AND MONTHS_BETWEEN(SYSDATE, h.NGAYBDHD) >= 24
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 2  -- English skill
        AND nk.CAPBAC >= 'B1'
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 1  -- Computer skill
        AND nk.CAPBAC >= 'B'
    );

    -- Update CAPBAC to Senior
    UPDATE NHANVIEN n
    SET n.CAPBAC = 'Senior'
    WHERE n.CAPBAC = 'Junior'
    AND EXISTS (
        SELECT *
        FROM HOPDONG h
        WHERE h.MANV = n.MANV
        AND MONTHS_BETWEEN(SYSDATE, h.NGAYBDHD) >= 60
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 2  -- English skill
        AND nk.CAPBAC >= 'B2'
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 1  -- Computer skill
        AND nk.CAPBAC >= 'C'
    );

    -- Update CAPBAC to Leader
    UPDATE NHANVIEN n
    SET n.CAPBAC = 'Leader'
    WHERE n.CAPBAC = 'Senior'
    AND EXISTS (
        SELECT *
        FROM HOPDONG h
        WHERE h.MANV = n.MANV
        AND MONTHS_BETWEEN(SYSDATE, h.NGAYBDHD) >= 84
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 2  -- English skill
        AND nk.CAPBAC >= 'B2'
    )
    AND EXISTS (
        SELECT *
        FROM NHANVIEN_KYNANG nk
        WHERE nk.MANV = n.MANV
        AND nk.MAKN = 1  -- Computer skill
        AND nk.CAPBAC >= 'C'
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE pro_xoaYCPT (MAYCIP IN YEUCAU.MAYC%TYPE) AS
    
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    DELETE YEUCAU WHERE MAYC = MAYCIP;
    SYS.SLEEP(10);
    commit;
END;
/
CREATE OR REPLACE PROCEDURE pro_duyetYCPT (MAYCIP IN YEUCAU.MAYC%TYPE) AS
    
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = MAYCIP;
    SYS.SLEEP(10);
    commit;

END;
/
CREATE OR REPLACE PROCEDURE pro_duyetYCLU (MAYCIP IN YEUCAU.MAYC%TYPE) AS

    
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = MAYCIP;
    SYS.SLEEP(10);
    commit;

END;
/
CREATE OR REPLACE PROCEDURE pro_capNhatYCLU (MANVIP IN YEUCAU.MANV%TYPE, MAYCIP IN YEUCAU.MAYC%TYPE, NOIDUNGIP IN YEUCAU.NOIDUNG%TYPE) AS
    
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE YEUCAU SET NOIDUNG = NOIDUNGIP, TRANGTHAI = 0 WHERE MAYC = MAYCIP and MANV = MANVIP;
    SYS.SLEEP(10);
    commit;
END;
/

CREATE OR REPLACE PROCEDURE PRO_CAPNHATYCNONREPEAT(
    p_MAYC IN YEUCAU.MAYC%TYPE
)
IS
    start_time TIMESTAMP;
    end_time TIMESTAMP;
    elapsed_time NUMBER;
BEGIN
    start_time := SYSTIMESTAMP;
    
    UPDATE YEUCAU
    SET TRANGTHAI = 1
    WHERE MAYC = p_MAYC;
    
    end_time := SYSTIMESTAMP;
    elapsed_time := ROUND(EXTRACT(SECOND FROM (end_time - start_time)), 2);
    
    IF elapsed_time > 10 THEN
        RAISE_APPLICATION_ERROR(-20001, 'TIMEOUT BECAUSE TABLE WAS LOCKED ');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
ROLLBACK;

RAISE;

end;
/








--Insert phòng ban
INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'IT',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);

INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'Marketing',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);

INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'Nhân sự',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);

INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'CSKH',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);

INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'Kỹ thuật',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);

INSERT INTO phongban VALUES (
    my_sequence_phongban.NEXTVAL,
    'Tài chính',
    TO_DATE('2024-05-23', 'YYYY-MM-DD'),
    NULL,
    NULL
);




--insert nhân viên
INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Đặng Quang Khánh Linh',
    'Nam',
    '2003-02-15',
    '0355662648',
    '22520756@gm.uit.edu.vn',
    'Long Thành, Đồng Nai',
    '075204005999',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Nguyễn Văn Hòa',
    'Nam',
    '2003-05-13',
    '0355662777',
    '22520454@gm.uit.edu.vn',
    'Rạch Giá, Kiên Giang',
    '075204005888',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Lê Tiến Đạt',
    'Nam',
    '2003-09-13',
    '0355662757',
    '22520214@gm.uit.edu.vn',
    'Tân Uyên, Bình Dương',
    '075204015888',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Huỳnh Nhật Duy',
    'Nam',
    TO_DATE('1990-01-01', 'YYYY-MM-DD'),
    '0901234560',
    '22520314@gm.uit.edu.vn',
    'Hà Nội',
    '001008786902',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Trần Thị Bích',
    'Nữ',
    TO_DATE('1988-02-02', 'YYYY-MM-DD'),
    '0901234561',
    'tranthibich@example.com',
    'Hồ Chí Minh',
    '001008786982',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Nguyễn Văn Cường',
    'Nam',
    TO_DATE('1991-03-03', 'YYYY-MM-DD'),
    '0901234562',
    'nguyenvancuong@example.com',
    'Đà Nẵng',
    '001005586982',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Phạm Thị Diệu',
    'Nữ',
    TO_DATE('1989-04-04', 'YYYY-MM-DD'),
    '0901234563',
    'phamthidieu@example.com',
    'Cần Thơ',
    '001008799982',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Hoàng Văn Dũng',
    'Nam',
    TO_DATE('1987-05-05', 'YYYY-MM-DD'),
    '0901234564',
    'hoangvandung@example.com',
    'Hải Phòng',
    '001008786900',
    'Fresher',
    2
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Đặng Thị Hạnh',
    'Nữ',
    TO_DATE('1992-06-06', 'YYYY-MM-DD'),
    '0901234565',
    'dangthihanh@example.com',
    'Huế',
    '001008786901',
    'Fresher',
    2
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Bùi Văn Khôi',
    'Nam',
    TO_DATE('1993-07-07', 'YYYY-MM-DD'),
    '0901234566',
    'buivankhoi@example.com',
    'Vinh',
    '001008786903',
    'Fresher',
    3
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Ngô Thị Lan',
    'Nữ',
    TO_DATE('1994-08-08', 'YYYY-MM-DD'),
    '0901234567',
    'ngothilan@example.com',
    'Nha Trang',
    '001008786904',
    'Fresher',
    3
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Phan Văn Minh',
    'Nam',
    TO_DATE('1988-09-09', 'YYYY-MM-DD'),
    '0901234568',
    'phanvanminh@example.com',
    'Quảng Ninh',
    '001008786905',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Dương Thị Ngọc',
    'Nữ',
    TO_DATE('1989-10-10', 'YYYY-MM-DD'),
    '0901234569',
    'duongthingoc@example.com',
    'Bắc Ninh',
    '001008786906',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Vũ Văn Phong',
    'Nam',
    TO_DATE('1990-11-11', 'YYYY-MM-DD'),
    '0901234570',
    'vuvanphong@example.com',
    'Hà Nội',
    '001008786907',
    'Fresher',
    3
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Lê Thị Quỳnh',
    'Nữ',
    TO_DATE('1991-12-12', 'YYYY-MM-DD'),
    '0901234571',
    'lethiquynh@example.com',
    'Hồ Chí Minh',
    '001008786909',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Nguyễn Văn Sơn',
    'Nam',
    TO_DATE('1992-01-13', 'YYYY-MM-DD'),
    '0901234572',
    'nguyenvanson@example.com',
    'Đà Nẵng',
    '001008786910',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Trần Thị Thảo',
    'Nữ',
    TO_DATE('1993-02-14', 'YYYY-MM-DD'),
    '0901234573',
    'tranthithao@example.com',
    'Cần Thơ',
    '001008786911',
    'Fresher',
    5
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Phạm Văn Tâm',
    'Nam',
    TO_DATE('1994-03-15', 'YYYY-MM-DD'),
    '0901234574',
    'phamvantam@example.com',
    'Hải Phòng',
    '001008786912',
    'Fresher',
    4
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Hoàng Thị Uyên',
    'Nữ',
    TO_DATE('1995-04-16', 'YYYY-MM-DD'),
    '0901234575',
    'hoangthiuyen@example.com',
    'Huế',
    '001008786913',
    'Fresher',
    4
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Bùi Văn Vinh',
    'Nam',
    TO_DATE('1996-05-17', 'YYYY-MM-DD'),
    '0901234576',
    'buivanvinh@example.com',
    'Vinh',
    '001008786914',
    'Fresher',
    4
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Ngô Văn Xuyên',
    'Nam',
    TO_DATE('1997-06-18', 'YYYY-MM-DD'),
    '0901234577',
    'ngovanxuyen@example.com',
    'Nha Trang',
    '001008786915',
    'Fresher',
    2
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Phan Thị Yến',
    'Nữ',
    TO_DATE('1998-07-19', 'YYYY-MM-DD'),
    '0901234578',
    'phanthiyen@example.com',
    'Quảng Ninh',
    '001008786916',
    'Fresher',
    4
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Dương Văn Khải',
    'Nam',
    TO_DATE('1999-08-20', 'YYYY-MM-DD'),
    '0901234579',
    'duongvankhai@example.com',
    'Bắc Ninh',
    '001008786917',
    'Fresher',
    6
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Vũ Thị Hằng',
    'Nữ',
    TO_DATE('2000-09-21', 'YYYY-MM-DD'),
    '0901234580',
    'vuthihang@example.com',
    'Hà Nội',
    '001008786918',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Đỗ Văn Bình',
    'Nam',
    TO_DATE('2001-10-22', 'YYYY-MM-DD'),
    '0901234581',
    'dovanbinh@example.com',
    'Hồ Chí Minh',
    '001008786919',
    'Fresher',
    5
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Lê Thị Hoa',
    'Nữ',
    TO_DATE('2002-11-23', 'YYYY-MM-DD'),
    '0901234582',
    'lethihoa@example.com',
    'Đà Nẵng',
    '001008786920',
    'Fresher',
    4
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Nguyễn Văn Hùng',
    'Nam',
    TO_DATE('2003-12-24', 'YYYY-MM-DD'),
    '0901234583',
    'nguyenvanhung@example.com',
    'Cần Thơ',
    '001008786921',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Trần Thị Kim',
    'Nữ',
    TO_DATE('2004-01-25', 'YYYY-MM-DD'),
    '0901234584',
    'tranthikim@example.com',
    'Hải Phòng',
    '001008786922',
    'Fresher',
    1
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Phạm Văn Long',
    'Nam',
    TO_DATE('2002-02-26', 'YYYY-MM-DD'),
    '0901234585',
    'phamvanlong@example.com',
    'Huế',
    '001008786923',
    'Fresher',
    2
);

INSERT INTO nhanvien VALUES (
    my_sequence_nhanvien.NEXTVAL,
    'Hoàng Thị Mai',
    'Nữ',
    TO_DATE('2002-03-27', 'YYYY-MM-DD'),
    '0901234586',
    'hoangthimai@example.com',
    'Vinh',
    '001008786924',
    'Fresher',
    3
);

INSERT INTO taikhoan VALUES (
    my_sequence_taikhoan.NEXTVAL,
    1,
    'admin',
    'admin',
    'quản lý'
);

INSERT INTO taikhoan VALUES (
    my_sequence_taikhoan.NEXTVAL,
    2,
    'vanhoa',
    '123',
    'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    3,
    'nhanvien03',
    'password03',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    4,
    'nhanvien04',
    'password04',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    5,
    'nhanvien05',
    'password05',
    N'nhân viên'
);

INSERT INTO kynang VALUES (
    my_sequence_kynang.NEXTVAL,
    'tin học'
);

INSERT INTO kynang VALUES (
    my_sequence_kynang.NEXTVAL,
    'tiếng anh'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    6,
    'nhanvien06',
    'password06',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    7,
    'nhanvien07',
    'password07',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    8,
    'nhanvien08',
    'password08',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    9,
    'nhanvien09',
    'password09',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    10,
    'nhanvien10',
    'password10',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    11,
    'nhanvien11',
    'password11',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    12,
    'nhanvien12',
    'password12',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    13,
    'nhanvien13',
    'password13',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    14,
    'nhanvien14',
    'password14',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    15,
    'nhanvien15',
    'password15',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    16,
    'nhanvien16',
    'password16',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    17,
    'nhanvien17',
    'password17',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    18,
    'nhanvien18',
    'password18',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    19,
    'nhanvien19',
    'password19',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    20,
    'nhanvien20',
    'password20',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    21,
    'nhanvien21',
    'password21',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    22,
    'nhanvien22',
    'password22',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    23,
    'nhanvien23',
    'password23',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    24,
    'nhanvien24',
    'password24',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    25,
    'nhanvien25',
    'password25',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    26,
    'nhanvien26',
    'password26',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    27,
    'nhanvien27',
    'password27',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    28,
    'nhanvien28',
    'password28',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    29,
    'nhanvien29',
    'password29',
    N'nhân viên'
);

INSERT INTO taikhoan (
    matk,
    manv,
    tentk,
    matkhau,
    loaitaikhoan
) VALUES (
    my_sequence_taikhoan.NEXTVAL,
    30,
    'nhanvien30',
    'password30',
    N'nhân viên'
);

--insert chấm công
INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    1,
    5,
    29,
    1,
    3,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    2,
    5,
    29,
    1,
    5,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    3,
    5,
    29,
    0,
    4,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    4,
    5,
    29,
    1,
    2,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    5,
    5,
    29,
    1,
    1,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    6,
    5,
    29,
    0,
    3,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    7,
    5,
    21,
    1,
    4,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    8,
    5,
    20,
    2,
    5,
    2
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    9,
    5,
    23,
    0,
    2,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    10,
    5,
    24,
    1,
    1,
    3
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    11,
    5,
    22,
    2,
    3,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    12,
    5,
    29,
    0,
    4,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    13,
    5,
    30,
    0,
    5,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    14,
    5,
    23,
    2,
    2,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    15,
    5,
    29,
    0,
    10,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    16,
    5,
    29,
    1,
    10,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    17,
    5,
    21,
    2,
    4,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    18,
    5,
    20,
    0,
    5,
    2
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    19,
    5,
    23,
    1,
    2,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    20,
    5,
    24,
    2,
    1,
    3
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    21,
    5,
    22,
    1,
    3,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    22,
    5,
    21,
    2,
    4,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    23,
    5,
    29,
    0,
    20,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    24,
    5,
    23,
    1,
    2,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    25,
    5,
    24,
    2,
    1,
    3
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    26,
    5,
    22,
    1,
    3,
    0
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    27,
    5,
    21,
    2,
    4,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    28,
    5,
    20,
    0,
    5,
    2
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    29,
    5,
    23,
    1,
    2,
    1
);

INSERT INTO chamcong (
    manv,
    thanglamviec,
    songaylamviec,
    songaynghi,
    sogiotangca,
    songayditre
) VALUES (
    30,
    5,
    24,
    2,
    1,
    3
);












--insert hopdong
INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    1,
    TO_DATE('2022-05-31', 'YYYY-MM-DD'),
    add_months(sysdate, 24)
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    2,
    TO_DATE('2022-05-31', 'YYYY-MM-DD'),
    TO_DATE('2024-06-6', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    3,
    TO_DATE('2022-05-31', 'YYYY-MM-DD'),
    TO_DATE('2024-06-5', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    4,
    TO_DATE('2022-05-31', 'YYYY-MM-DD'),
    TO_DATE('2022-06-13', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    5,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    6,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    7,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    8,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    9,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    10,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    11,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    12,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    13,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    14,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    15,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    16,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    17,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    18,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    19,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    20,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    21,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    22,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    23,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    24,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    25,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    26,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    27,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    28,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    29,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);

INSERT INTO hopdong (
    mahd,
    manv,
    ngaybdhd,
    ngaykthd
) VALUES (
    my_sequence_hopdong.NEXTVAL,
    30,
    TO_DATE('2024-05-31', 'YYYY-MM-DD'),
    TO_DATE('2026-05-31', 'YYYY-MM-DD')
);



--insert nhanvien_Kynang
INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    1,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    2,
    'B2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    3,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    4,
    'B2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    5,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    6,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    7,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    8,
    'B2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    9,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    10,
    'C1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    11,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    12,
    'C2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    13,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    14,
    'A1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    15,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    16,
    'A2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    17,
    'C'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    18,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    19,
    'A'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    20,
    'B2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    21,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    22,
    'C1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    23,
    'C'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    24,
    'C2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    25,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    26,
    'A1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    27,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    28,
    'A2'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    29,
    'C'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    30,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    1,
    2,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    1,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    3,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    5,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    7,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    9,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    11,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    13,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    15,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    17,
    'B'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    19,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    21,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    23,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    25,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    27,
    'B1'
);

INSERT INTO nhanvien_kynang (
    makn,
    manv,
    capbac
) VALUES (
    2,
    29,
    'B1'
);


--insert yeucau
INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    1,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    2,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    3,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    4,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    5,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    6,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    7,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    8,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    9,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    10,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    11,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    12,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    13,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    14,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    15,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    16,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    17,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    18,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    19,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

INSERT INTO yeucau (
    mayc,
    manv,
    noidung,
    trangthai
) VALUES (
    my_sequence_yeucau.NEXTVAL,
    20,
    'Xin nghỉ phép ngày 31/05/2024 với lí do bị ốm',
    0
);

COMMIT;