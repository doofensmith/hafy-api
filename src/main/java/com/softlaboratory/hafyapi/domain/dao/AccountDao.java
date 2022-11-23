package com.softlaboratory.hafyapi.domain.dao;

import com.softlaboratory.hafyapi.domain.common.BaseDaoSoftDelete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "t_account_master")
@SQLDelete(sql = "update t_account_master set is_deleted=true, deleted_at=current_timestamp where id=?")
@Where(clause = "is_deleted=false")
public class AccountDao extends BaseDaoSoftDelete implements Serializable, UserDetails {

    private static final long serialVersionUID = 1990047253115992661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean isActive = true;

    @OneToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private ProfileDao profile;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bt_account_roles",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private List<RoleDao> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bt_account_types",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_type")
    )
    private List<TypeDao> types = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(roles.stream(), types.stream()).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
