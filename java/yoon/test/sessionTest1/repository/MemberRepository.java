package yoon.test.sessionTest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.sessionTest1.domain.Members;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {



}
