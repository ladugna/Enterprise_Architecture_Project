package ORS_Shipping.event;

import ORS_Shipping.domain.TraceRecord;
import ORS_Shipping.service.TraceRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class serviceListener {

    @Autowired
    TraceRecordServiceImpl traceRecordService;
    @EventListener
    @Async
    public void onServiceEvent(TraceRecord traceRecord){

        traceRecordService.saveRecord(traceRecord);
    }
}
