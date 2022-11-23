package com.softlaboratory.hafyapi.config.domain.dao;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.config.domain.common.BaseDaoSoftDelete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "t_account_type")
@SQLDelete(sql = "update t_account_type set is_deleted=true, deleted_at=current_timestamp where id=?")
@Where(clause = "is_deleted=false")
public class TypeDao extends BaseDaoSoftDelete implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = -6043347839139151562L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private AccountTypeEnum type;

    @Override
    public String getAuthority() {
        return type.name();
    }

}