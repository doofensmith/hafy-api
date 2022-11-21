package com.softlaboratory.hafyapi.domain.dao;

import com.softlaboratory.hafyapi.domain.common.BaseDaoSoftDelete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "t_account_master")
@SQLDelete(sql = "update t_account_master set is_deleted=true, deleted_at=current_timestamp where id=?")
@Where(clause = "is_deleted=false")
public class AccountDao extends BaseDaoSoftDelete implements Serializable {

    private static final long serialVersionUID = 1990047253115992661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(name = "id_profile", nullable = false)
    private Long idProfile;

    //@ElementCollection(targetClass = Long.class)
    //@JoinTable(name = "t_account_role", joinColumns = @JoinColumn(name = "id"))
    //@JoinColumn(name = "id_roles", nullable = false, referencedColumnName = "id")
    //private List<Long> idRoles;

    //@JoinColumn(name = "id_types", nullable = false, referencedColumnName = "id")
    //private List<Long> idTypes;
}
