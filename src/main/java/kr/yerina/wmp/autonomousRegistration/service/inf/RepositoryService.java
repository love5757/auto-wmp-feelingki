package kr.yerina.wmp.autonomousRegistration.service.inf;

import kr.yerina.wmp.autonomousRegistration.entity.Member;
import kr.yerina.wmp.autonomousRegistration.entity.Team;

import java.util.List;

public interface RepositoryService {
    Team addTeam(Team team);

    Team findTeamBySeq(int seq);

    Member addMember(Member member, int seq);

    List<Team> findTeamAll();

}
