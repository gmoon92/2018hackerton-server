package com.ahea.nurikabe.member;

import javax.validation.ValidationException;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Integer createMember(Member member) {
        Member savedMember = this.memberRepository.save(member);
        return savedMember.getId();
    }

	public void join(Member member) {
		
		Member user = this.findUserId(member);
		
		if(user == null) {
			this.memberRepository.save(member);
		}else {
			throw new ValidationException("중복된 아이디가 있습니다.");
		}
		
	}

	public void login(Member member) {
		Member loginUser = this.findMember(member);
		 
		if(loginUser == null) { throw new ValidationException("해당 회원을 찾을 수 없습니다."); }
		
		Member acceptMember = this.findByUserIdAndUserPassword(member);
		
		if(acceptMember == null) { throw new ValidationException("아이디 또는 비밀번호를 확인하세요."); }
	}
	
	public Member findByUserIdAndUserPassword(Member member) {
		return this.memberRepository.findByUserIdAndUserPassword(member.getUserId()
																,member.getUserPassword());
	}
	public Member findUserId(Member member) {
		return this.memberRepository.findByUserId(member.getUserId());
	}
	
	public Member findMember(Member member) {
		return this.memberRepository.findOne(member.getId());
	}
}
