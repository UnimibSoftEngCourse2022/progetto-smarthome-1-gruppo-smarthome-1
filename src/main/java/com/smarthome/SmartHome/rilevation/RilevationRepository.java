package com.smarthome.SmartHome.rilevation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.smarthome.SmartHome.Device.Device;

@Repository
public interface RilevationRepository extends JpaRepository<Rilevation, Long> {
    
    
    @Query("SELECT r FROM Rilevation r WHERE id = ?1")
    Optional<Rilevation> findRilevationById(Long id);
    
    //@Query("SELECT * FROM rilevation r JOIN device d ON r. = d. WHERE d.id=")
    Optional<List<Rilevation>> findRilevationByDevice(Device device);

    //@Query("")
    //void saveRilevation(Rilevation rilevation);

    @Query(value="COUNT(*) FROM Rilevation", nativeQuery=true)
    int countRilevation();

    @Query(value="COUNT(*) FROM Rilevation r WHERE r.id=?1", nativeQuery=true)
    int countRilevationById(long id);

}
