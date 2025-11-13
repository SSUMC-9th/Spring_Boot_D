package ssu.cromi.umc9th.domain.test.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssu.cromi.umc9th.domain.test.exception.TestException;
import ssu.cromi.umc9th.domain.test.exception.code.TestErrorCode;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService{

    @Override
    public void checkFlag(Long flag){
        if(flag==1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
