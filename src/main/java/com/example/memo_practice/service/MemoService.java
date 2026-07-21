package com.example.memo_practice.service;

import com.example.memo_practice.dto.MemoRequestDto;
import com.example.memo_practice.dto.MemoResponseDto;
import com.example.memo_practice.entity.Memo;
import com.example.memo_practice.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @RequiredArgsConstructor // final 변수를 bean으로 등록
public class MemoService { // memoService 이름으로 bean 역할로 저장된다.
    // 1. 생성자 주입
    private final MemoRepository memoRepository;
    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }
    // 2. Method 주입
    private MemoRepository memoRepository2; // 변경 가능하도록
    @Autowired
    public void setDi(MemoRepository memoRepository) {
        this.memoRepository2 = memoRepository;
    }

    // 3. field 주입
    @Autowired
    private MemoRepository memoRepository3;

    private MemoRepository memoRepository4;
    // 4. 직접 Autowired 에 해당하는 구현을 작성
//    @Autowired
//    public MemoService(ApplicationContext context) {
//        // 1. 'Bean' 이름으로 가져오기
//        // MemoRepository memoRepository4 = (MemoRepository) context.getBean("memoRepository4");
//
//        // 2. 'Bean' 클래스 형식으로 가져오기
//        MemoRepository memoRepository4 = context.getBean(MemoRepository.class);
//        this.memoRepository = memoRepository4;
//    }




    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if(memo != null) {
            // memo 내용 수정
            memoRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if(memo != null) {
            // memo 삭제
            memoRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
