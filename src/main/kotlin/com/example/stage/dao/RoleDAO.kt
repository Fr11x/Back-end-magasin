package com.example.stage.dao

import com.example.stage.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleDAO : JpaRepository<Role, Long> {

}
