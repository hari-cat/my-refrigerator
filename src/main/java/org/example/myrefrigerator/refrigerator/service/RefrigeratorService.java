package org.example.myrefrigerator.refrigerator.service;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorRegisterRequest;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.example.myrefrigerator.refrigerator.repository.RefrigeratorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;

    public void saveRefrigerator(RefrigeratorRegisterRequest request) {
        Refrigerator refrigerator = Refrigerator.builder().name(request.name()).ownerId(request.ownerId()).build();

        /** @TODO 유저정보가 존재하는지 체크하는 로직 필요 */
        if(refrigeratorRepository.existsRefrigeratorsByOwnerId(request.ownerId())){
            throw new IllegalArgumentException("이미 나만의 냉장고가 존재합니다.");
        }

        refrigeratorRepository.save(refrigerator);
    }

}
