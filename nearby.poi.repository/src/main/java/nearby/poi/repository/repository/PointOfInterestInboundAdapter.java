package nearby.poi.repository.repository;

import nearby.poi.domain.PointOfInterest;
import nearby.poi.interfaces.PointOfInterestRepository;
import nearby.poi.repository.jpa.PointOfInterestEntity;
import nearby.poi.repository.mapper.PointOfInterestMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class PointOfInterestInboundAdapter implements PointOfInterestRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<PointOfInterest> findAllPointsOfInterest() {
        Stream<PointOfInterestEntity> entityPointOfInterests = entityManager
                .createQuery("select t from PointOfInterestEntity t", PointOfInterestEntity.class)
                .getResultStream();
        return entityPointOfInterests.map(PointOfInterestMapper::convertEntityToPointOfInterest).collect(Collectors.toList());
    }

}
