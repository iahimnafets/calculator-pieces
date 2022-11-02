package service;

import com.calculator.dto.PoundsAndPenceDTO;

import com.calculator.exception.ApiRequestException;
import com.calculator.exception.MessageError;
import com.calculator.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith( MockitoJUnitRunner.class )
@Slf4j
public class ServiceTest {


    @InjectMocks
    private Service service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void transformPence_calculateAllData() {

        log.info("transformPence_calculateAllData: Begin");

        Map<String, Integer> expectPounds = new HashMap<>();
        expectPounds.put("1",1);
        expectPounds.put("2", 0);
        expectPounds.put("5", 0);
        expectPounds.put("10", 0);
        expectPounds.put("20", 0);
        expectPounds.put("50", 10);

        Map<String, Integer> expectPence = new HashMap<>();
        expectPence.put("1",0);
        expectPence.put("2", 1);
        expectPence.put("5", 0);
        expectPence.put("10", 0);
        expectPence.put("20", 1);
        expectPence.put("50", 0);

        PoundsAndPenceDTO poundsAndPenceExpect = PoundsAndPenceDTO.builder()
                .pence(expectPence)
                .pounds(expectPounds)
                .externalID("UUID_1")
                .penceSubmitted( new BigDecimal(50122))
                .build();

        PoundsAndPenceDTO poundsAndPenceDTO  =  service.transformPence( new BigDecimal(50122), "UUID_1" );

        assertThat( poundsAndPenceDTO.getPounds() ,
                is( poundsAndPenceExpect.getPounds() ));

        assertThat( poundsAndPenceDTO.getPence() ,
                is( poundsAndPenceExpect.getPence() ));

        assertThat( poundsAndPenceDTO.getExternalID() ,
                is( poundsAndPenceExpect.getExternalID() ));

        assertThat( poundsAndPenceDTO.getPenceSubmitted() ,
                is( poundsAndPenceExpect.getPenceSubmitted() ));
    }


    @Test
    public void transformPence_ParametersNull() {
        log.info("transformPence_BigDecimalWitchDecimal: Begin");
        try {
            service.transformPence( null, null );
            assertTrue(false);

        } catch (ApiRequestException e) {
            log.info((e.getMessage()));
            if ( MessageError.ID_AND_PENCE_ARE_MANDATORY.getMessage().startsWith(e.getMessage()) ){ assertTrue(true); }
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void transformPence_BigDecimalWitchDecimal() {
        log.info("transformPence_ParametersNull: Begin");
        try {
            service.transformPence( new BigDecimal(222.1), "UUU_234");
            assertTrue(false);

        } catch (ApiRequestException e) {
            log.info((e.getMessage()));
            if ( MessageError.PENCE_NO_DECIMALS .getMessage().startsWith(e.getMessage()) ){ assertTrue(true); }
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }

}
