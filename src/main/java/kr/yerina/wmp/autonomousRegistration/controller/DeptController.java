package kr.yerina.wmp.autonomousRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeptController {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 팀 등록 폼 페이지
     * @param model
     * @return
     */
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "create your team!");
        model.addAttribute("team", new Team());
        return "form";
    }

    /**
     * 팀 저장 후 결과 조회
     * @param team
     * @param model
     * @return
     */
    @PostMapping("/add")
    public String add(@ModelAttribute Team team, Model model) {
        Team entity = repositoryService.addTeam(team);

        model.addAttribute("result", entity);
        return "result";
    }

    /**
     * 사용자 정보 입력 폼
     * @param seq
     * @param model
     * @return
     */
    @GetMapping("/addUser/{seq}")
    public String addUser(@PathVariable int seq, Model model) {
        Team team = repositoryService.findTeamBySeq(seq);
        model.addAttribute("title", "add user");
        model.addAttribute("team", team);
        model.addAttribute("member", new Member());

        return "addUser";
    }


    /**
     * 사용자 정보 저장
     * @param member
     * @param seq
     * @param model
     * @return
     */
    @PostMapping("/insert")
    public String insert(@ModelAttribute Member member, @RequestParam int seq,  Model model) {
        Member entity = repositoryService.addMember(member, seq);

        model.addAttribute("result", entity);
        return "user";
    }

    /**
     * 팀 목록 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Team> teamList = repositoryService.findTeamAll();
        model.addAttribute("teamList", teamList);

        return "list";
    }

}
