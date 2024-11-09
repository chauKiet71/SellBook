package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hpsf.Decimal;

import java.util.List;

@Entity
@Table(name = "vi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vi {

    @Id
    String id_vi;
    Float so_tien;
    int id_tai_khoan;

    @JsonIgnore
    @OneToMany(mappedBy = "vi")
    List<Transaction> transactions;
}
