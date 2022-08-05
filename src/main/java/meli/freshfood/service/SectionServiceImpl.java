package meli.freshfood.service;
import meli.freshfood.model.Section;
import meli.freshfood.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public boolean isSectionValid(Section section) {
        if (section != null) {
            return true;
        }
        return false;
    }
}