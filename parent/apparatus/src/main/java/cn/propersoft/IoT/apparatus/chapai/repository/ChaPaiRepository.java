package cn.propersoft.IoT.apparatus.chapai.repository;

import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaPaiRepository extends JpaRepository<ChaPaiEntity, String> {

//    @Query(" SELECT ChaPaiEntity FROM ChaPaiEntity ORDER BY addTime DESC ")
//    Page<ChaPaiEntity> findOneByOrderByAddTimeDesc(Pageable pageable);
}
