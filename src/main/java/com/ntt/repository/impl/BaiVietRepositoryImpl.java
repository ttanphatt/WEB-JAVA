/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.HinhAnh;
import com.ntt.pojo.NguoiDung;
import com.ntt.pojo.TrangThaiBaiViet;

import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.TaiKhoanRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThanhThuyen
 */
@Repository
@Transactional
public class BaiVietRepositoryImpl implements BaiVietRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private TaiKhoanRepository taikhoan;
    @Autowired
    private BaiVietRepository baivietRepo;

    @Override
    public List<BaiViet> getBaiViet2(String tenBaiViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!tenBaiViet.isEmpty()) {
            Predicate p = builder.equal(root.get("tenBaiViet").as(String.class), tenBaiViet.trim());
            query = query.where(p);
        }
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object> getBaiVietByType(String loaiBViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root rPost = q.from(BaiViet.class);
        q.select(rPost);

        Predicate p = b.equal(rPost.get("loaiBaiViet"), Integer.parseInt(loaiBViet));
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        org.hibernate.query.Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object> getBaiViet2Type(int loaiBViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root rPost = q.from(BaiViet.class);
        q.select(rPost);

        Predicate p = b.equal(rPost.get("loaiBaiViet"), loaiBViet);
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        q.orderBy(b.desc(rPost.get("id")));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();

        try {

            s.save(baiviet);
            HinhAnh hinhanh = new HinhAnh();
            hinhanh.setIdBaiViet(baiviet);
            hinhanh.setDuongDan(baiviet.getHinhAnh());
            s.save(hinhanh);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        TrangThaiBaiViet newTT = new TrangThaiBaiViet();
        newTT.setId(1);

        try {

            baiviet.setLoaiTrangThai(newTT);
            s.update(baiviet);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteBaiViet(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        BaiViet p = this.baivietRepo.getBaiVietById(id);
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    

    @Override
    public List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM BaiViet WHERE idNguoiDung= :idNguoiDung");
        q.setParameter("idNguoiDung", idNgDung);
        return q.getResultList();
    }

    @Override
    public BaiViet addBaiVietAPI(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(baiviet);

        return baiviet;

    }

    @Override
    public List<BaiViet> getBaiVietTK(String address, BigDecimal price, Integer soNguoi) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<BaiViet> q = b.createQuery(BaiViet.class);

        Root<BaiViet> root = q.from(BaiViet.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (address != null) {
            predicates.add(b.like(root.get("diaChiCt"), String.format("%%%s%%", address)));

        }
        // Thêm các đi�?u kiện tìm kiếm vào danh sách predicates

        if (price != null) {
            predicates.add(b.equal(root.get("giaThue"), price));
        }
        if (soNguoi != null) {
            predicates.add(b.equal(root.get("soNguoi"), soNguoi));
        }

        // Kết hợp tất cả các đi�?u kiện bằng AND
        Predicate finalPredicate = b.and(predicates.toArray(new Predicate[0]));

        q.where(finalPredicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<BaiViet> getBaiVietByGiaThue(BigDecimal gia) {
        try (Session session = factory.getObject().getCurrentSession()) {
            String hql = "FROM BaiViet b WHERE b.giaThue <= :gia";
            return session.createQuery(hql, BaiViet.class)
                    .setParameter("gia", gia)
                    .list();
        }
    }

    @Override
    public List<BaiViet> getBaiVietAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From BaiViet");
        return q.getResultList();
    }

    @Override
    public List<BaiViet> getBaiVietGia(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<BaiViet> q = b.createQuery(BaiViet.class);

        Root<BaiViet> root = q.from(BaiViet.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String gia = params.get("gia");
            if (gia != null && !gia.isEmpty()) {
                predicates.add(b.equal(root.get("giaThue"), Long.parseLong(gia)));
            }
            String address = params.get("address");
            if (address != null && !address.isEmpty()) {
                predicates.add(b.like(root.get("diaChiCt"), String.format("%%%s%%", address)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<BaiViet> getBaiVietGiaChuaDuyet() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT bv FROM BaiViet bv JOIN bv.loaiTrangThai lt WHERE lt.id = :trangThaiId")
                .setParameter("trangThaiId", 2);

        return q.getResultList();
    }

    @Override
    public boolean updateTrangThai(BaiViet idBaiViet) {
        Session s = this.factory.getObject().getCurrentSession();
        TrangThaiBaiViet newTT = new TrangThaiBaiViet();
        newTT.setId(1);

        try {
            idBaiViet.setLoaiTrangThai(newTT);
            s.update(idBaiViet);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void saveBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(baiviet);
    }

    @Override
    public void deleteBaiVietByNguoiDung(NguoiDung nguoidung) {
        Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM BaiViet bl WHERE bl.idNguoiDung = :nguoidung";
        session.createQuery(hql)
                .setParameter("nguoidung", nguoidung)
                .executeUpdate();
    }

    @Override
    public BaiViet getBaiVietById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM BaiViet WHERE id =: id");
        q.setParameter("id", id);
        return (BaiViet) q.getSingleResult();
    }

    @Override
    public List<BaiViet> getBaiViet() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From BaiViet");
        return q.getResultList();
    }

    @Override
    public List<BaiViet> getBaiViet(String tenBaiViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!tenBaiViet.isEmpty()) {
            Predicate p = builder.equal(root.get("tenBaiViet").as(String.class), tenBaiViet.trim());
            query = query.where(p);
        }

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    

}
