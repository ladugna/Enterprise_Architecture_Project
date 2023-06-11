package retail.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.domain.TraceRecord;
import retail.repository.TraceRecordRepository;

@Service
public class TraceRecordServiceImpl implements TraceRecordService{

    @Autowired
    TraceRecordRepository traceRecordRepository;
    @Override
    public void saveRecord(TraceRecord traceRecord) {
        traceRecordRepository.save(traceRecord);
    }
}

