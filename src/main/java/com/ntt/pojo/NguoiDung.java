/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThanhThuyen
 */
@Entity
@Table(name = "nguoi_dung")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NguoiDung.findAll", query = "SELECT n FROM NguoiDung n"),
    @NamedQuery(name = "NguoiDung.findById", query = "SELECT n FROM NguoiDung n WHERE n.id = :id"),
    @NamedQuery(name = "NguoiDung.findByTenNguoiDung", query = "SELECT n FROM NguoiDung n WHERE n.tenNguoiDung = :tenNguoiDung"),
    @NamedQuery(name = "NguoiDung.findByEmail", query = "SELECT n FROM NguoiDung n WHERE n.email = :email"),
    @NamedQuery(name = "NguoiDung.findBySdt", query = "SELECT n FROM NguoiDung n WHERE n.sdt = :sdt"),
    @NamedQuery(name = "NguoiDung.findByTenTaiKhoan", query = "SELECT n FROM NguoiDung n WHERE n.tenTaiKhoan = :tenTaiKhoan"),
    @NamedQuery(name = "NguoiDung.findByMatKhau", query = "SELECT n FROM NguoiDung n WHERE n.matKhau = :matKhau"),
    @NamedQuery(name = "NguoiDung.findByAvatar", query = "SELECT n FROM NguoiDung n WHERE n.avatar = :avatar"),
    @NamedQuery(name = "NguoiDung.findByHinhAnh", query = "SELECT n FROM NguoiDung n WHERE n.hinhAnh = :hinhAnh")})
public class NguoiDung implements Serializable {

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the xacNhanMatKhau
     */
    public String getXacNhanMatKhau() {
        return xacNhanMatKhau;
    }

    /**
     * @param xacNhanMatKhau the xacNhanMatKhau to set
     */
    public void setXacNhanMatKhau(String xacNhanMatKhau) {
        this.xacNhanMatKhau = xacNhanMatKhau;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ten_nguoi_dung")
    private String tenNguoiDung;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "sdt")
    private String sdt;
    @Size(max = 100)
    @Column(name = "ten_tai_khoan")
    private String tenTaiKhoan;
    @Size(max = 200)
    @Column(name = "mat_khau")
    private String matKhau;
    @Size(max = 500)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 500)
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @Transient
    private MultipartFile file;
    @Transient
    private String xacNhanMatKhau;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<BinhLuan> binhLuanSet;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<TieuChi> tieuChiSet;
    @JoinColumn(name = "id_loai_tai_khoan", referencedColumnName = "id")
    @ManyToOne
    private LoaiTaiKhoan idLoaiTaiKhoan;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<ThongBao> thongBaoSet;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<BaiViet> baiVietSet;
    @OneToMany(mappedBy = "idChuTro")
    @JsonIgnore
    private Set<Follow> followSet;
    @OneToMany(mappedBy = "idKhachHang")
    @JsonIgnore
    private Set<Follow> followSet1;

//    @Transient
//    private String username;
//    
//    @Transient
//    private String password;

    public NguoiDung() {
    }

    public NguoiDung(Integer id) {
        this.id = id;
    }

    public NguoiDung(Integer id, String tenNguoiDung, String email, String sdt, String tenTaiKhoan, String matKhau, String avatar, String hinhAnh, MultipartFile file, String xacNhanMatKhau, Set<BinhLuan> binhLuanSet, Set<TieuChi> tieuChiSet, LoaiTaiKhoan idLoaiTaiKhoan, Set<ThongBao> thongBaoSet, Set<BaiViet> baiVietSet, Set<Follow> followSet, Set<Follow> followSet1) {
        this.id = id;
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.sdt = sdt;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.avatar = avatar;
        this.hinhAnh = hinhAnh;
        this.file = file;
        this.xacNhanMatKhau = xacNhanMatKhau;
        this.binhLuanSet = binhLuanSet;
        this.tieuChiSet = tieuChiSet;
        this.idLoaiTaiKhoan = idLoaiTaiKhoan;
        this.thongBaoSet = thongBaoSet;
        this.baiVietSet = baiVietSet;
        this.followSet = followSet;
        this.followSet1 = followSet1;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @XmlTransient
    public Set<BinhLuan> getBinhLuanSet() {
        return binhLuanSet;
    }

    public void setBinhLuanSet(Set<BinhLuan> binhLuanSet) {
        this.binhLuanSet = binhLuanSet;
    }

    @XmlTransient
    public Set<TieuChi> getTieuChiSet() {
        return tieuChiSet;
    }

    public void setTieuChiSet(Set<TieuChi> tieuChiSet) {
        this.tieuChiSet = tieuChiSet;
    }

    public LoaiTaiKhoan getIdLoaiTaiKhoan() {
        return idLoaiTaiKhoan;
    }

    public void setIdLoaiTaiKhoan(LoaiTaiKhoan idLoaiTaiKhoan) {
        this.idLoaiTaiKhoan = idLoaiTaiKhoan;
    }

    @XmlTransient
    public Set<ThongBao> getThongBaoSet() {
        return thongBaoSet;
    }

    public void setThongBaoSet(Set<ThongBao> thongBaoSet) {
        this.thongBaoSet = thongBaoSet;
    }

    @XmlTransient
    public Set<BaiViet> getBaiVietSet() {
        return baiVietSet;
    }

    public void setBaiVietSet(Set<BaiViet> baiVietSet) {
        this.baiVietSet = baiVietSet;
    }

    @XmlTransient
    public Set<Follow> getFollowSet() {
        return followSet;
    }

    public void setFollowSet(Set<Follow> followSet) {
        this.followSet = followSet;
    }

    @XmlTransient
    public Set<Follow> getFollowSet1() {
        return followSet1;
    }

    public void setFollowSet1(Set<Follow> followSet1) {
        this.followSet1 = followSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoiDung)) {
            return false;
        }
        NguoiDung other = (NguoiDung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.NguoiDung[ id=" + id + " ]";
    }

//    /**
//     * @return the username
//     */
//    public String getUsername() {
//        return username;
//    }
//
//    /**
//     * @param username the username to set
//     */
//    public void setUsername(String username) {
//        this.username = tenTaiKhoan;
//    }
//
//    /**
//     * @return the password
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * @param password the password to set
//     */
//    public void setPassword(String password) {
//        this.password = matKhau;
//    }

}
