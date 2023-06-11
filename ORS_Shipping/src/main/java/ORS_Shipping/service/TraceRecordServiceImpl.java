package ORS_Shipping.service;

import ORS_Shipping.domain.TraceRecord;
import ORS_Shipping.respository.TraceRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraceRecordServiceImpl implements TraceRecordService{

    @Autowired
    TraceRecordRepository traceRecordRepository;
    @Override
    public void saveRecord(TraceRecord traceRecord) {
        traceRecordRepository.save(traceRecord);
    }
}
