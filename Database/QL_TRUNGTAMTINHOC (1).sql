create database QL_TRUNGTAMTINHOC
go
use QL_TRUNGTAMTINHOC
go

CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV NVARCHAR(500),
    ChucVu NVARCHAR(50),
    NgaySinh DATE,
    GioiTinh NVARCHAR(10),
    DiaChi NVARCHAR(100),
    SDT VARCHAR(10),
    Email VARCHAR(50),
	NgayVL DATE,
);

CREATE TABLE GiaoVien (
    MaGV VARCHAR(10) PRIMARY KEY,
    TenGV NVARCHAR(50),
    NgaySinh DATE,
    DiaChi NVARCHAR(100),
    Email VARCHAR(50),
	TrinhDo VARCHAR(50),
	NgayVL DATE,
);


CREATE TABLE TaiKhoan (
    MaTK VARCHAR(10) PRIMARY KEY,
    MatKhau VARCHAR(100),
	MaNV VARCHAR(10) FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
	MaGV VARCHAR(10) FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV),
    LoaiTaiKhoan VARCHAR(2) check ( LoaiTaiKhoan in ('NV', 'GV'))
);

CREATE TABLE KhoaHoc (
    MaKH VARCHAR(10) PRIMARY KEY,
    TenKH NVARCHAR(100),
    NgayBatDau DATE,
    NgayKetThuc DATE,
    HocPhi DECIMAL(18,2),
    SoLgLop INT, 
	SoBuoiHoc INT,
	MaNV VARCHAR(10) FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
);

CREATE TABLE CaHoc (
    MaCaHoc VARCHAR(10) PRIMARY KEY,
    TenCaHoc NVARCHAR(50),
    TgianBatDau TIME,
    TgianKetThuc TIME
);

CREATE TABLE PhongHoc (
    MaPhong VARCHAR(10) PRIMARY KEY,
    TenPhong VARCHAR(50),
    TinhTrang NVARCHAR(100),
    SucChua INT
);

CREATE TABLE Lop (
    MaLop VARCHAR(10) PRIMARY KEY,
    TenLop NVARCHAR(50),
	SoLgHV INT,
	MaCaHoc VARCHAR(10) FOREIGN KEY (MaCaHoc) REFERENCES CaHoc(MaCaHoc),
    MaKH VARCHAR(10) FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH),
    MaGV VARCHAR(10) FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV),
	MaPH VARCHAR(10) FOREIGN KEY (MaPH) REFERENCES PhongHoc(MaPhong),
);

 CREATE TABLE HocVien (
    MaHV VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(100),
    NgaySinh DATE,
    GioiTinh NVARCHAR(10),
    SDT VARCHAR(10),
    DiaChi NVARCHAR(200),
    NgayDangKy DATE,
	MaLop VARCHAR(10) FOREIGN KEY (MaLop) REFERENCES Lop(MaLop),
);

CREATE TABLE BienLai (
    MaBL VARCHAR(10) PRIMARY KEY,
    NgayTao DATE,
    SoTien DECIMAL(18, 2),
    PhuongThucThanhToan NVARCHAR(50),
    MaHV VARCHAR(10) FOREIGN KEY (MaHV) REFERENCES HocVien(MaHV),
    MaNV VARCHAR(10) FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
);

CREATE TABLE DSHocVienVang (
	MaLop VARCHAR(10) FOREIGN KEY REFERENCES Lop(MaLop),
    MaHV VARCHAR(10) FOREIGN KEY REFERENCES HocVien(MaHV),
	NgayDiemDanh DATE,
    GhiChu NVARCHAR(200),
	PRIMARY KEY (MaLop, maHV, NgayDiemDanh)
);

CREATE TABLE KyThi (
    MaKyThi VARCHAR(10) PRIMARY KEY,
	TenKyThi VARCHAR(20),
	NgayBatDau DATE,
	NgayKetThuc DATE,
    HinhThucThi NVARCHAR(50),
    GhiChu NVARCHAR(200),
	MaKH VARCHAR(10) FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH),
	MaNV VARCHAR(10) FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE LichThi (
    MaLichThi VARCHAR(10) PRIMARY KEY,
	NgayThi DATE,
	TgianBatDau TIME,
	TgianKetThuc TIME,
	MaGV VARCHAR(10) FOREIGN KEY REFERENCES GiaoVien(MaGV),
	MaKyThi VARCHAR(10) FOREIGN KEY (MaKyThi) REFERENCES KyThi(MaKyThi),
	MaPhong VARCHAR(10) FOREIGN KEY REFERENCES PhongHoc(MaPhong),
);

CREATE TABLE ThamGiaThi (
    MaHV VARCHAR(10) FOREIGN KEY REFERENCES HocVien(MaHV),
    MaLichThi VARCHAR(10) FOREIGN KEY REFERENCES LichThi(MaLichThi),
    PRIMARY KEY (MaHV, MaLichThi)
);

CREATE TABLE KetQua (
    MaHV VARCHAR(10) FOREIGN KEY (MaHV) REFERENCES HocVien(MaHV),
    MaKyThi VARCHAR(10) FOREIGN KEY (MaKyThi) REFERENCES KyThi(MaKyThi),
	MaGV VARCHAR(10) FOREIGN KEY REFERENCES GiaoVien(MaGV),
    Diem DECIMAL(5, 2),
    NgayCham DATE,
    GhiChu NVARCHAR(200),
    PRIMARY KEY (MaHV, MaKyThi, MaGV)
);



INSERT INTO NhanVien (MaNV, TenNV, ChucVu, NgaySinh, GioiTinh, DiaChi, SDT, Email, NgayVL) VALUES
('NV001', N'Nguyễn Văn An', N'Quản lý', '1985-03-15', N'Nam', N'123 Lê Lợi, Quận 1, TP.HCM', '0901234567', 'an.nv@example.com', '2010-01-01'),
('NV002', N'Trần Thị Bình', N'Nhân viên lễ tân', '1990-07-22', N'Nữ', N'45 Nguyễn Huệ, Quận 1, TP.HCM', '0912345678', 'binh.tt@example.com', '2012-06-15'),
('NV003', N'Phạm Quốc Cường', N'Kế toán', '1988-11-10', N'Nam', N'78 Trần Hưng Đạo, Quận 5, TP.HCM', '0923456789', 'cuong.pq@example.com', '2015-03-20'),
('NV004', N'Lê Thị Dung', N'Hành chính', '1992-04-05', N'Nữ', N'12 Pasteur, Quận 3, TP.HCM', '0934567890', 'dung.lt@example.com', '2018-09-10'),
('NV005', N'Hoàng Văn Em', N'IT Support', '1995-12-25', N'Nam', N'56 Võ Văn Tần, Quận 3, TP.HCM', '0945678901', 'em.hv@example.com', '2020-02-01');

-- Insert into GiaoVien
INSERT INTO GiaoVien (MaGV, TenGV, NgaySinh, DiaChi, Email, TrinhDo, NgayVL) VALUES
('GV001', N'Nguyễn Thành Đạt', '1980-05-20', N'101 Lý Thường Kiệt, Quận 10, TP.HCM', 'dat.nt@example.com', N'Thạc sĩ CNTT', '2011-07-01'),
('GV002', N'Trần Thị Hoa', '1983-09-15', N'23 Nguyễn Trãi, Quận 5, TP.HCM', 'hoa.tt@example.com', N'Cử nhân CNTT', '2013-04-10'),
('GV003', N'Phạm Minh Khang', '1978-02-28', N'67 Điện Biên Phủ, Quận 3, TP.HCM', 'khang.pm@example.com', N'Thạc sĩ CNTT', '2009-11-15'),
('GV004', N'Lê Thị Lan', '1985-06-30', N'89 Hai Bà Trưng, Quận 1, TP.HCM', 'lan.lt@example.com', N'Cử nhân CNTT', '2014-08-20'),
('GV005', N'Võ Văn Nam', '1982-12-12', N'34 Nguyễn Đình Chiểu, Quận 1, TP.HCM', 'nam.vv@example.com', N'Thạc sĩ CNTT', '2010-03-01');

-- Tài khoản cho Nhân viên, mật khẩu là '123'
INSERT INTO TaiKhoan (MaTK, MatKhau, MaNV, MaGV, LoaiTaiKhoan) VALUES
('TK001', '123', 'NV001', NULL, 'NV'),
('TK002', '123', 'NV002', NULL, 'NV'),
('TK003', '123', 'NV003', NULL, 'NV'),
('TK004', '123', 'NV004', NULL, 'NV'),
('TK005', '123', 'NV005', NULL, 'NV');

-- Tài khoản cho Giáo viên, mật khẩu là '123'
INSERT INTO TaiKhoan (MaTK, MatKhau, MaNV, MaGV, LoaiTaiKhoan) VALUES
('TK006', '123', NULL, 'GV001', 'GV'),
('TK007', '123', NULL, 'GV002', 'GV'),
('TK008', '123', NULL, 'GV003', 'GV'),
('TK009', '123', NULL, 'GV004', 'GV'),
('TK010', '123', NULL, 'GV005', 'GV');

-- Insert into KhoaHoc
INSERT INTO KhoaHoc (MaKH, TenKH, NgayBatDau, NgayKetThuc, HocPhi, SoLgLop, SoBuoiHoc, MaNV) VALUES
('KH001', N'Lập trình Python Cơ bản', '2025-01-10', '2025-03-10', 5000000.00, 2, 20, 'NV001'),
('KH002', N'Thiết kế đồ họa Photoshop', '2025-02-01', '2025-04-01', 6000000.00, 1, 15, 'NV002'),
('KH003', N'Lập trình Java Nâng cao', '2025-03-15', '2025-06-15', 8000000.00, 3, 25, 'NV001'),
('KH004', N'Excel Nâng cao', '2025-04-01', '2025-05-30', 4000000.00, 2, 12, 'NV003'),
('KH005', N'An ninh mạng Cơ bản', '2025-05-01', '2025-07-01', 7000000.00, 1, 18, 'NV002');

-- Insert into CaHoc
INSERT INTO CaHoc (MaCaHoc, TenCaHoc, TgianBatDau, TgianKetThuc) VALUES
('CH001', N'Ca Sáng', '08:00:00', '10:00:00'),
('CH002', N'Ca Trưa', '10:30:00', '12:30:00'),
('CH003', N'Ca Chiều', '13:30:00', '15:30:00'),
('CH004', N'Ca Tối 1', '18:00:00', '20:00:00'),
('CH005', N'Ca Tối 2', '20:00:00', '22:00:00');

-- Insert into PhongHoc
INSERT INTO PhongHoc (MaPhong, TenPhong, TinhTrang, SucChua) VALUES
('PH001', 'Phòng A1', N'Hoạt động', 20),
('PH002', 'Phòng A2', N'Hoạt động', 25),
('PH003', 'Phòng B1', N'Bảo trì', 15),
('PH004', 'Phòng B2', N'Hoạt động', 30),
('PH005', 'Phòng C1', N'Hoạt động', 20);

-- Insert into Lop
INSERT INTO Lop (MaLop, TenLop, SoLgHV, MaCaHoc, MaKH, MaGV, MaPH) VALUES
('L001', N'Lớp Python CB 01', 15, 'CH001', 'KH001', 'GV001', 'PH001'),
('L002', N'Lớp Photoshop 01', 10, 'CH004', 'KH002', 'GV002', 'PH002'),
('L003', N'Lớp Java NC 01', 20, 'CH003', 'KH003', 'GV003', 'PH004'),
('L004', N'Lớp Excel NC 01', 12, 'CH002', 'KH004', 'GV004', 'PH001'),
('L005', N'Lớp An ninh mạng 01', 1, 'CH005', 'KH005', 'GV005', 'PH002');

-- Insert into HocVien
INSERT INTO HocVien (MaHV, HoTen, NgaySinh, GioiTinh, SDT, DiaChi, NgayDangKy, MaLop) VALUES
('HV001', N'Nguyễn Thị Mai', '2000-05-10', N'Nữ', '0987654321', N'123 Nguyễn Văn Cừ, Quận 5, TP.HCM', '2025-01-05', 'L001'),
('HV002', N'Trần Văn Hùng', '1998-08-20', N'Nam', '0976543210', N'45 Lý Tự Trọng, Quận 1, TP.HCM', '2025-01-25', 'L002'),
('HV003', N'Phạm Thị Ngọc', '2001-03-15', N'Nữ', '0965432109', N'78 Lê Đại Hành, Quận 11, TP.HCM', '2025-03-01', 'L003'),
('HV004', N'Lê Văn Tài', '1999-11-30', N'Nam', '0954321098', N'12 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', '2025-03-25', 'L004'),
('HV005', N'Hoàng Thị Lan', '2002-07-07', N'Nữ', '0943210987', N'56 Bùi Thị Xuân, Quận 1, TP.HCM', '2025-04-20', 'L005');

-- Insert into BienLai
INSERT INTO BienLai (MaBL, NgayTao, SoTien, PhuongThucThanhToan, MaHV, MaNV) VALUES
('BL001', '2025-01-05', 5000000.00, N'Chuyển khoản', 'HV001', 'NV001'),
('BL002', '2025-01-25', 6000000.00, N'Tiền mặt', 'HV002', 'NV002'),
('BL003', '2025-03-01', 8000000.00, N'Chuyển khoản', 'HV003', 'NV001'),
('BL004', '2025-03-25', 4000000.00, N'Tiền mặt', 'HV004', 'NV003'),
('BL005', '2025-04-20', 7000000.00, N'Chuyển khoản', 'HV005', 'NV002');
---ds hoc vien vang
INSERT INTO DSHocVienVang (MaLop, MaHV, NgayDiemDanh, GhiChu) VALUES
('L001', 'HV001', '2025-01-20', N'Nghỉ ốm'),
('L002', 'HV002', '2025-02-05', N'Việc gia đình'),
('L003', 'HV003', '2025-03-25', N'Không lý do'),
('L004', 'HV004', '2025-04-10', N'Nghỉ phép'),
('L005', 'HV005', '2025-05-15', N'Bận công việc');

-- Insert into KyThi
INSERT INTO KyThi (MaKyThi, TenKyThi, NgayBatDau, NgayKetThuc, HinhThucThi, GhiChu, MaKH, MaNV) VALUES
('KT001', 'Thi Python CB', '2025-03-15', '2025-03-15', N'Trắc nghiệm', N'Thi cuối khóa', 'KH001', 'NV001'),
('KT002', 'Thi Photoshop', '2025-04-05', '2025-04-05', N'Thực hành', N'Thi thực hành', 'KH002', 'NV002'),
('KT003', 'Thi Java NC', '2025-06-20', '2025-06-20', N'Trắc nghiệm', N'Thi cuối khóa', 'KH003', 'NV001'),
('KT004', 'Thi Excel NC', '2025-06-01', '2025-06-01', N'Thực hành', N'Thi thực hành', 'KH004', 'NV003'),
('KT005', 'Thi An ninh mạng', '2025-07-05', '2025-07-05', N'Trắc nghiệm', N'Thi cuối khóa', 'KH005', 'NV002');

-- Insert into LichThi
INSERT INTO LichThi (MaLichThi, NgayThi, TgianBatDau, TgianKetThuc, MaGV, MaKyThi, MaPhong) VALUES
('LT001', '2025-03-15', '08:00:00', '10:00:00', 'GV001', 'KT001', 'PH001'),
('LT002', '2025-04-05', '13:00:00', '15:00:00', 'GV002', 'KT002', 'PH002'),
('LT003', '2025-06-20', '09:00:00', '11:00:00', 'GV003', 'KT003', 'PH004'),
('LT004', '2025-06-01', '14:00:00', '16:00:00', 'GV004', 'KT004', 'PH001'),
('LT005', '2025-07-05', '08:30:00', '10:30:00', 'GV005', 'KT005', 'PH002');

-- Insert into ThamGiaThi
INSERT INTO ThamGiaThi (MaHV, MaLichThi) VALUES
('HV001', 'LT001'),
('HV002', 'LT002'),
('HV003', 'LT003'),
('HV004', 'LT004'),
('HV005', 'LT005');

-- Insert into KetQua
INSERT INTO KetQua (MaHV, MaKyThi, MaGV, Diem, NgayCham, GhiChu) VALUES
('HV001', 'KT001', 'GV001', 8.50, '2025-03-16', N'Đạt'),
('HV002', 'KT002', 'GV002', 7.00, '2025-04-06', N'Đạt'),
('HV003', 'KT003', 'GV003', 6.50, '2025-06-21', N'Đạt'),
('HV004', 'KT004', 'GV004', 9.00, '2025-06-02', N'Xuất sắc'),
('HV005', 'KT005', 'GV005', 8.00, '2025-07-06', N'Đạt');





