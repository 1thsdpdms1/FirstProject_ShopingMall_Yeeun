package org.spring.e1i4TeamProject.admin.repository;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByUserEmail(String userEmail);



    Page<MemberEntity> findByRole(Pageable pageable, Role role);

    @Query(value = "select m from MemberEntity m where m.role = :role and m.name = :name")
    Page<MemberEntity> findByRoleAndNameContains(Pageable pageable, String search, Role role);

    Page<MemberEntity> findByNameContains(Pageable pageable, String search);

    Page<MemberEntity> findByPhoneNumberContains(Pageable pageable, String search);

    Page<MemberEntity> findByUserEmailContains(Pageable pageable, String search);


//    @Query(value = "select m from MemberEntity m where m.role = :role and m.name = :name")
//    Page<MemberEntity> findByRoleAndName(@Param("role") Role role, @Param("name") String name, Pageable pageable);

//    @Query(value = "select m from MemberEntity m where m.role=:role")
//    Page<MemberEntity> findByRole(Pageable pageable, @Param("role")Role role );
}
