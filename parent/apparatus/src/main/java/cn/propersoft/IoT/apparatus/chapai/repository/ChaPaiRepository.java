package cn.propersoft.IoT.apparatus.chapai.repository;

import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChaPaiRepository extends JpaRepository<ChaPaiEntity, String> {

    @Query(value = " SELECT C FROM ChaPaiEntity C WHERE C.addTime >= :startTime AND C.addTime <= :endTime ")
    Page<ChaPaiEntity> getFloorData(@Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime, Pageable pageable);


    @Query(value = " SELECT C FROM ChaPaiEntity C  ")
    Page<ChaPaiEntity> getFloorData2(Pageable pageable);
}
