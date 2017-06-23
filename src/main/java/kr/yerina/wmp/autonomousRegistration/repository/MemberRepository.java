package kr.yerina.wmp.autonomousRegistration.repository;

import kr.yerina.wmp.autonomousRegistration.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}

