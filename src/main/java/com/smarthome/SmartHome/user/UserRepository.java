package com.smarthome.SmartHome.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//                                                    | Questa è la classe che voglio gestire.
//                                                    |     | Questo è il tipo della variabile che fa da id in User.
//                                                    |     |
@Repository //                                        V     V
public interface UserRepository extends JpaRepository<User, Long> {

}
