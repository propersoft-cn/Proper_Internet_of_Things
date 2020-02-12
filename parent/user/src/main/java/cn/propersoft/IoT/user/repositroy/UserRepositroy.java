package cn.propersoft.IoT.user.repositroy;

import cn.propersoft.IoT.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositroy extends JpaRepository<UserEntity, String> {

    @Modifying
    void deleteByIdIn(List<String> idList);

    Optional<UserEntity> findByUsername(String username);
}
