import org.example.myrefrigerator.refrigerator.dto.RefrigeratorResponse;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.example.myrefrigerator.refrigerator.repository.RefrigeratorRepository;
import org.example.myrefrigerator.refrigerator.service.RefrigeratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RefrigeratorServiceTest {
    @Mock
    private RefrigeratorRepository refrigeratorRepository;

    @InjectMocks
    private RefrigeratorService refrigeratorService;

    @Test
    void getRefrigeratorSuccess(){
        // given
        Refrigerator refrigerator = Refrigerator.builder().ownerId(1L).name("나만의 냉장고").build();

        given(refrigeratorRepository.findActiveRefrigerator(1L)).willReturn(Optional.of(refrigerator));

        // when
        RefrigeratorResponse result = refrigeratorService.getRefrigerator(1L);

        // then
        assertThat(result.name()).isEqualTo("나만의 냉장고");

    }


}