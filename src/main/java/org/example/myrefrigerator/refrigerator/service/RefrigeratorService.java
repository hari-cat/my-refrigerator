package org.example.myrefrigerator.refrigerator.service;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorRegisterRequest;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorResponse;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.example.myrefrigerator.refrigerator.repository.RefrigeratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;

    @Transactional
    public void saveRefrigerator(RefrigeratorRegisterRequest request) {
        Refrigerator refrigerator = Refrigerator.builder().name(request.name()).ownerId(request.ownerId()).build();

        if (refrigeratorRepository.existsRefrigeratorsByOwnerIdAndStatus(request.ownerId(), Status.ACTIVE)) {
            throw new IllegalArgumentException("이미 나만의 냉장고가 존재합니다.");
        }

        refrigeratorRepository.save(refrigerator);
    }

    public RefrigeratorResponse getRefrigerator(Long id) {
        Refrigerator refrigerator = refrigeratorRepository.findActiveRefrigerator(id).orElseThrow(() -> new IllegalArgumentException("회원님의 냉장고가 존재하지 않습니다."));

        return RefrigeratorResponse.from(refrigerator);
    }

    @Transactional
    public void deleteRefrigerator(Long id){
        Refrigerator refrigerator = refrigeratorRepository.findActiveRefrigerator(id).orElseThrow(() -> new IllegalArgumentException("회원님의 냉장고가 존재하지 않습니다."));

        refrigerator.delete();
    }

}
