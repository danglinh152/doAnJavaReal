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
drop constraint nhanvien_mapb_fk
/
alter table phongban
drop constraint phongban_matruongphong_fk
/
drop table phongban
/
drop table nhanvien
/
ALTER SESSION set NLS_DATE_FORMAT = 'DD-MM-YYYY';
set feedback ON
set serveroutput on;


create table nhanvien (
MANV            number(4),
HOTEN           Nvarchar2(30),
GIOITINH        Nvarchar2(3),
NGSINH          date,
SDT             varchar(15),
EMAIL           varchar(30),
DIACHI          varchar(50),
CCCD            varchar(12),
CAPBAC          varchar(10),
MAPB            number(4),
constraint nhanvien_pk primary key (manv),
constraint nhanvien_sdt_uk unique (sdt),
constraint nhanvien_email_uk unique (email),
constraint nhanvien_cccd_uk unique (cccd),
constraint nhanvien_gioitinh_ck check (gioitinh in ('nam', N'n?'))
)
/

create table hopdong (
mahd            number(4),
manv            number(4),
ngaybdhd        date,
ngaykthd        date
)
/

create table phongban (
mapb            number(4),
tenpb           nvarchar2(20),
ngthanhlap      date,
matruongphong   number(4),
ngaynhanchuc    date,
constraint phongban_pk primary key (mapb)
)
/

create table taikhoan (
matk            number(4),
manv            number(4),
tentk           nvarchar2(10),
matkhau         nvarchar2(10),
loaitaikhoan    nvarchar2(10),
constraint taikhoan_pk primary key (matk),
constraint taikhoan_tentk_uk unique (tentk),
constraint taikhoan_loaitk_ck check (loaitaikhoan in (N'qu?n l�', N'nh�n vi�n'))
)
/

create table kynang (
makn            number(4),
tenkn           nvarchar2(20),
constraint kynang_pk primary key (makn),
constraint kynang_tenkn check (tenkn in (N'tin h?c', N'ngo?i ng?'))
)
/

create table nhanvien_kynang (
makn            number(4),
manv            number(4),
capbac          char(1),
constraint nhanvien_kynang_pk primary key (makn, manv),
constraint nhanvien_kynang_capbac_ck check (capbac in ('A', 'B', 'C', '1', '2', '3'))
)
/

create table chamcong (
macc            number(4),
manv            number(4),
thanglamviec    number(2),
songaylamviec   number(2),
songaynghi      number(2),
sogiotangca     number(2),
songayditre     number(2),
constraint chamcong_pk primary key (macc),
constraint chamcong_thanglamviec_ck check (thanglamviec >=1 and thanglamviec <= 12),
constraint chamcong_songaylamviec_ck check (songaylamviec > 0 and songaylamviec <= 31),
constraint chamcong_songaynghi_ck check (songaynghi > 0 and songaynghi <= 31),
constraint chamcong_sogiotangca_ck check (sogiotangca > 0 and sogiotangca <= 48)
)
/






ALTER TABLE hopdong 
   ADD CONSTRAINT hopdong_manv_fk
   FOREIGN KEY (manv) REFERENCES nhanvien (manv);
ALTER TABLE nhanvien 
   ADD CONSTRAINT nhanvien_mapb_fk
   FOREIGN KEY (mapb) REFERENCES phongban (mapb);
ALTER TABLE phongban
   ADD CONSTRAINT phongban_matruongphong_fk
   FOREIGN KEY (matruongphong) REFERENCES nhanvien (manv);
ALTER TABLE taikhoan
   ADD CONSTRAINT taikhoan_manv_fk
   FOREIGN KEY (manv) REFERENCES nhanvien (manv);
ALTER TABLE nhanvien_kynang
   ADD CONSTRAINT nhanvien_kynang_manv_fk
   FOREIGN KEY (manv) REFERENCES nhanvien (manv);
ALTER TABLE nhanvien_kynang
   ADD CONSTRAINT nhanvien_kynang_makn_fk
   FOREIGN KEY (makn) REFERENCES kynang (makn);
ALTER TABLE chamcong
   ADD CONSTRAINT chamcong_manv_fk
   FOREIGN KEY (manv) REFERENCES nhanvien (manv);





--Trigger Ng� y nh?n ch?c c?a tr??ng phòng ph?i l?n h?n ng� y sinh c?a ng??i ?ó
CREATE OR REPLACE TRIGGER TRG_NGNHANCHUC_NGSINH
BEFORE INSERT OR UPDATE ON PHONGBAN
FOR EACH ROW
DECLARE OUT_NGNHANCHUC PHONGBAN.NGAYNHANCHUC%TYPE;
        OUT_NGAYSINH NHANVIEN.NGSINH%TYPE;
BEGIN
    SELECT :NEW.NGAYNHANCHUC, NV.NGSINH
    INTO OUT_NGNHANCHUC, OUT_NGAYSINH
    FROM  NHANVIEN NV
    WHERE :NEW.MATRUONGPHONG = NV.MANV;
    
    IF (OUT_NGNHANCHUC < OUT_NGAYSINH) THEN
    RAISE_APPLICATION_ERROR(-20001, 'Ngay nhan chuc phai lon hon ngay sinh');
    ELSE DBMS_OUTPUT.PUT_LINE('Them/sua du lieu thanh cong');
    END IF;
END;

--Trigger Ng� y nh?n ch?c c?a tr??ng phòng ph?i l?n h?n ho?c b?ng ng� y th� nh l?p c?a phòng ban ?ó
CREATE OR REPLACE TRIGGER TRG_NGNHANCHUC_NGTHANHLAP
BEFORE INSERT OR UPDATE ON PHONGBAN
FOR EACH ROW
BEGIN
    IF :NEW.NGAYNHANCHUC < :NEW.NGAYTHANHLAP THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngay nhan chuc phai lon hon hoac bang ngay thanh lap cua phong ban');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('Thêm/s?a d? li?u th� nh công');
    END IF;
END;

--Trigger M?i nhân viên không có quá 10 k? n?ng
CREATE OR REPLACE TRIGGER TRG_NV_KN
BEFORE INSERT OR UPDATE ON NHANVIEN_KYNANG
FOR EACH ROW
DECLARE count_skill NUMBER;
BEGIN
    SELECT COUNT (*)
    INTO count_skill
    FROM NHANVIEN_KYNANG 
    WHERE MANV = :NEW.MANV;
    
    IF(count_skill >= 10) THEN
    DBMS_OUTPUT.PUT_LINE('M?i nhân viên không có quá 10 k? n?ng');
    ELSE DBMS_OUTPUT.PUT_LINE('Thêm/s?a d? li?u th� nh công');
    END IF;
END;


--NGÀY B?T ??U LÀM VI?C C?A 1 NH N VIÊN PH?I L?N H?N NGÀY SINH
CREATE OR REPLACE TRIGGER TG_1 
BEFORE INSERT OR UPDATE ON NHANVIEN
    FOR EACH ROW
        BEGIN        
             IF(:NEW.NGAYBD < :NEW.NGSINH) THEN
                     RAISE_APPLICATION_ERROR(-20001, 'L?i');
                ELSE
                    DBMS_OUTPUT.PUT_LINE('THANH CONG!');
                END IF;
        END;     


--S? ng� y ?i l� m, ?i tr?, v?ng không ???c quá
-- N?u tháng có 30 ng� y: không ???c quá 30 ng� y
-- N?u tháng có 31 ng� y: không ???c quá 31 ng� y
-- N?u tháng 2: không ???c quá 29 ng� y
CREATE OR REPLACE TRIGGER TG_2
BEFORE INSERT OR UPDATE ON CHAMCONG
    FOR EACH ROW
    BEGIN
        IF(:NEW.THANGLAMVIEC IN (4, 6, 9, 11) AND (:NEW.SONGAYLV > 30 OR :NEW.SONGAYDITRE > 30 OR :NEW.SONGAYNGHI > 30)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC IN (1, 3, 5, 7, 8, 10, 12) AND (:NEW.SONGAYLV > 31 OR :NEW.SONGAYDITRE > 31 OR :NEW.SONGAYNGHI > 31)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSIF(:NEW.THANGLAMVIEC = 2 AND (:NEW.SONGAYLV > 29 OR :NEW.SONGAYDITRE > 29 OR :NEW.SONGAYNGHI > 29)) THEN
             RAISE_APPLICATION_ERROR(-20001, 'L?i');
        ELSE
            DBMS_OUTPUT.PUT_LINE('THANH CONG');
        END IF;
    END;   
    
    
--Thu?c tính NGSINH c?a NHANVIEN ph?i nh? h?n ho?c b?ng ng� y hi?n t?i
CREATE OR REPLACE TRIGGER TG_3
BEFORE INSERT OR UPDATE ON NHANVIEN
    FOR EACH ROW
        BEGIN
            IF(:NEW.NGSINH > SYSDATE) THEN
                 RAISE_APPLICATION_ERROR(-20001, 'L?i');
            ELSE
                DBMS_OUTPUT.PUT_LINE('THANHCONG');
            END IF;
        END;
            
            
        
--M?i tr??ng phòng ch? l� m tr??ng phòng duy nh?t 1 phòng ban
CREATE OR REPLACE TRIGGER TG_4
BEFORE INSERT OR UPDATE ON PHONGBAN
    FOR EACH ROW
    DECLARE NUMBER_OF_TRP NUMBER;
        BEGIN 
            SELECT COUNT(*) INTO NUMBER_OF_TRP
            FROM PHONGBAN P 
            INNER JOIN NHANVIEN N
            ON P.MATRUONGPHONG = N.MANV            
            WHERE MATRUONGPHONG = :NEW.MATRUONGPHONG;
            IF(NUMBER_OF_TRP > 0) THEN
                RAISE_APPLICATION_ERROR(-20001, 'L?i');
            ELSE
                DBMS_OUTPUT.PUT_LINE('THANHCONG');
            END IF;
        END;


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


----trigger tr??ng phòng ph?i có kinh nghi?m trên 6 n?m
--CREATE OR REPLACE TRIGGER CHECK_TRUONGPHONG
--BEFORE INSERT OR UPDATE ON NHANVIEN
--FOR EACH ROW
--DECLARE
--    v_kinhnghiem NUMBER;
--    v_matruongphong NUMBER;
--    v_ngaynhan_chuc DATE;
--BEGIN
--    SELECT NGAYNHANCHUC,MATRUONGPHONG INTO v_ngaynhan_chuc,v_matruongphong
--    FROM PHONGBAN
--    WHERE MATRUONGPHONG = :NEW.MANV;
--
--    IF :NEW.MANV = v_matruongphong THEN
--        v_kinhnghiem := EXTRACT(YEAR FROM v_ngaynhan_chuc) - EXTRACT(YEAR FROM :NEW.NGAYBD);
--        
--        IF v_kinhnghiem < 6 THEN
--            RAISE_APPLICATION_ERROR(-20002, 'Tr??ng phòng ph?i có ít nh?t 6 n?m kinh nghi?m.');
--        END IF;
--    END IF;
--END;


--trigger nhân viên v� o l� m ph?i t? ?? 18 tu?i
CREATE OR REPLACE TRIGGER CHECK_NHANVIEN_AGE
BEFORE INSERT OR UPDATE ON NHANVIEN
FOR EACH ROW
DECLARE
    v_tuoi NUMBER;
BEGIN
    -- Tính tu?i c?a nhân viên m?i
    v_tuoi := EXTRACT(YEAR FROM :NEW.NGAYBD) - EXTRACT(YEAR FROM :NEW.NGSINH);

    -- Ki?m tra n?u tu?i c?a nhân viên m?i d??i 18
    IF v_tuoi < 18 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Nhân viên ph?i t? ?? 18 tu?i ?? v� o l� m.');
    END IF;
END;


