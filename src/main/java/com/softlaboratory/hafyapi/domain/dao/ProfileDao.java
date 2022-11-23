package com.softlaboratory.hafyapi.config.domain.dao;

import com.softlaboratory.hafyapi.config.domain.common.BaseDaoSoftDelete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "t_account_profile")
@SQLDelete(sql = "update t_account_profile set is_deleted=true, deleted_at=current_timestamp where id=?")
@Where(clause = "is_deleted=false")
public class ProfileDao extends BaseDaoSoftDelete implements Serializable {

    private static final long serialVersionUID = -3867194805555390206L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String fullname;

    @Column(length = 50, unique = true)
    private String email;

    @Column(name = "phone_number", length = 15, unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "birth_place", length = 20)
    private String birthPlace;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
