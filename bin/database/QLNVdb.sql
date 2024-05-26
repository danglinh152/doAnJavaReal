drop table chamcong
/
drop table nhanvien_kynang
/
drop table kynang
/
drop table taikhoan
/
drop table hopdong
/
alter table nhanvien
drop constraint nhanvien_fk
/
alter table phongban
drop constraint phongban_fk
/
drop table phongban
/
drop table yeucau
/
drop table nhanvien
/
ALTER SESSION set NLS_DATE_FORMAT = 'YYYY-MM-DD';
set feedback ON
set serveroutput on;
/
drop sequence my_sequence_chamcong;
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
constraint hopdong_fk foreign key (manv) references NHANVIEN (manv)
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
macc            number(4),
manv            number(4) not null,
thanglamviec    number(2) not null,
songaylamviec   number(2) not null,
songaynghi      number(2) not null,
sogiotangca     number(2) not null,
songayditre     number(2) not null,
constraint chamcong_pk primary key (macc),
constraint chamcong_fk foreign key (manv) references NHANVIEN (manv),
constraint chamcong_thanglamviec_ck check (thanglamviec >=1 and thanglamviec <= 12),
constraint chamcong_songaylamviec_ck check (songaylamviec > 0 and songaylamviec <= 31),
constraint chamcong_songaynghi_ck check (songaynghi > 0 and songaynghi <= 31),
constraint chamcong_sogiotangca_ck check (sogiotangca > 0 and sogiotangca <= 48)
);
/

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
CREATE SEQUENCE my_sequence_chamcong
START WITH 1
INCREMENT BY 1;
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

--Insert
INSERT INTO PHONGBAN VALUES (my_sequence_phongban.NEXTVAL, 'IT', '2024-05-23', null, null);

INSERT INTO NHANVIEN VALUES (my_sequence_nhanvien.NEXTVAL, 'Đặng Quang Khánh Linh', 'Nam', '2004-02-15', '0355662648', '22520756@gm.uit.edu.vn', '1401/4, tổ 17, ấp Phước Hòa, xã Long Phước, huyện Long Thành, tỉnh Đồng Nai', '075204005999', 'Fresher', 1);
INSERT INTO NHANVIEN VALUES (my_sequence_nhanvien.NEXTVAL, 'Nguyễn Văn Hòa', 'Nam', '2004-02-15', '0355662777', '22520454@gm.uit.edu.vn', 'Rạch Giá, Kiên Giang', '075204005888', 'Fresher', 1);
INSERT INTO NHANVIEN VALUES (my_sequence_nhanvien.NEXTVAL, 'Nguyễn Văn Đạt', 'Nam', '2004-02-15', '0355662757', '222222@gm.uit.edu.vn', 'Rạch Giá, Kiên Giang', '075204015888', 'Fresher', 1);

INSERT INTO TAIKHOAN VALUES (my_sequence_taikhoan.NEXTVAL, 1, 'admin', 'admin', 'quản lý');
INSERT INTO TAIKHOAN VALUES (my_sequence_taikhoan.NEXTVAL, 2, 'honloa', 'cc', 'nhân viên');

INSERT INTO KYNANG VALUES (my_sequence_kynang.NEXTVAL, 'tin học');
INSERT INTO KYNANG VALUES (my_sequence_kynang.NEXTVAL, 'tiếng anh');












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
    DBMS_OUTPUT.PUT_LINE('Mỗi nhân viên không có quá 9 kỹ năng');
    ELSE DBMS_OUTPUT.PUT_LINE('Thêm kỹ năng thành công');
    END IF;
END;
/
--S? ng� y ?i l� m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng� y: không ???c quá 30 ng� y
-- N?u tháng có 31 ng� y: không ???c quá 31 ng� y
-- N?u tháng 2: không ???c quá 29 ng� y
CREATE OR REPLACE TRIGGER TG_CC_1
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
--S? ng� y ?i l� m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng� y: không ???c quá 30 ng� y
-- N?u tháng có 31 ng� y: không ???c quá 31 ng� y
-- N?u tháng 2: không ???c quá 29 ng� y
CREATE OR REPLACE TRIGGER TG_CC_2
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
    
--Thu?c tính NGSINH c?a NHANVIEN phải lớn hơn 18 tuổi
CREATE OR REPLACE TRIGGER TG_NV_1
BEFORE INSERT ON NHANVIEN
    FOR EACH ROW
    BEGIN
        IF(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :NEW.NGSINH) >= 18) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/      
CREATE OR REPLACE TRIGGER TG_NV_2
BEFORE UPDATE ON NHANVIEN
    FOR EACH ROW
    BEGIN
        IF(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :NEW.NGSINH) >= 18) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/   
CREATE OR REPLACE TRIGGER TG_PB_1
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
CREATE OR REPLACE TRIGGER TG_PB_2
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
--S? ng� y ?i l� m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng� y: không ???c quá 30 ng� y
-- N?u tháng có 31 ng� y: không ???c quá 31 ng� y
-- N?u tháng 2: không ???c quá 29 ng� y
CREATE OR REPLACE TRIGGER TG_CC_3
BEFORE INSERT ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (2) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');     
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/
--S? ng� y ?i l� m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng� y: không ???c quá 30 ng� y
-- N?u tháng có 31 ng� y: không ???c quá 31 ng� y
-- N?u tháng 2: không ???c quá 29 ng� y
CREATE OR REPLACE TRIGGER TG_CC_4
BEFORE UPDATE ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (2) AND (:NEW.SONGAYLAMVIEC + :NEW.SONGAYNGHI + :NEW.SONGAYDITRE + :NEW.SOGIOTANGCA/24 > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');     
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
/
CREATE OR REPLACE TRIGGER TG_HD_1
BEFORE INSERT ON HOPDONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.NGAYBDHD > SYSDATE OR :NEW.NGAYBDHD > :NEW.NGAYKTHD) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/    
CREATE OR REPLACE TRIGGER TG_HD_2
BEFORE UPDATE ON HOPDONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.NGAYBDHD > SYSDATE OR :NEW.NGAYBDHD > :NEW.NGAYKTHD) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANHCONG');
        END IF;
    END;
/    



