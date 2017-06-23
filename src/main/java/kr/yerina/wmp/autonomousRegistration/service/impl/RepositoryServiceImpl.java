package kr.yerina.wmp.autonomousRegistration.service.impl;

import kr.yerina.wmp.autonomousRegistration.entity.Member;
import kr.yerina.wmp.autonomousRegistration.entity.Team;
import kr.yerina.wmp.autonomousRegistration.repository.MemberRepository;
import kr.yerina.wmp.autonomousRegistration.repository.TeamRepository;
import kr.yerina.wmp.autonomousRegistration.service.inf.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    public RepositoryServiceImpl() {
    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findTeamBySeq(int seq) {
        return teamRepository.findOne(seq);
    }

    @Override
    public Member addMember(Member member, int seq) {
        Team team = teamRepository.findOne(seq);
        Member saveEntity = new Member(team, member.getName());
        return memberRepository.save(saveEntity);
    }

    @Override
    public List<Team> findTeamAll() {
        return teamRepository.findAll();
    }

}
