
package nearby.poi.soap.interfaces;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nearby.poi.soap.interfaces package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetClosestPointOfInterestResponse_QNAME = new QName("http://interfaces.soap.poi.nearby/", "getClosestPointOfInterestResponse");
    private final static QName _GetClosestPointOfInterest_QNAME = new QName("http://interfaces.soap.poi.nearby/", "getClosestPointOfInterest");
    private final static QName _GetPointOfInterestsGreaterThanCountResponse_QNAME = new QName("http://interfaces.soap.poi.nearby/", "getPointOfInterestsGreaterThanCountResponse");
    private final static QName _GetPointOfInterestsGreaterThanCount_QNAME = new QName("http://interfaces.soap.poi.nearby/", "getPointOfInterestsGreaterThanCount");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nearby.poi.soap.interfaces
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClosestPointOfInterestResponse }
     * 
     */
    public GetClosestPointOfInterestResponse createGetClosestPointOfInterestResponse() {
        return new GetClosestPointOfInterestResponse();
    }

    /**
     * Create an instance of {@link GetPointOfInterestsGreaterThanCount }
     * 
     */
    public GetPointOfInterestsGreaterThanCount createGetPointOfInterestsGreaterThanCount() {
        return new GetPointOfInterestsGreaterThanCount();
    }

    /**
     * Create an instance of {@link GetPointOfInterestsGreaterThanCountResponse }
     * 
     */
    public GetPointOfInterestsGreaterThanCountResponse createGetPointOfInterestsGreaterThanCountResponse() {
        return new GetPointOfInterestsGreaterThanCountResponse();
    }

    /**
     * Create an instance of {@link GetClosestPointOfInterest }
     * 
     */
    public GetClosestPointOfInterest createGetClosestPointOfInterest() {
        return new GetClosestPointOfInterest();
    }

    /**
     * Create an instance of {@link PointOfInterestDTO }
     * 
     */
    public PointOfInterestDTO createPointOfInterestDTO() {
        return new PointOfInterestDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClosestPointOfInterestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.poi.nearby/", name = "getClosestPointOfInterestResponse")
    public JAXBElement<GetClosestPointOfInterestResponse> createGetClosestPointOfInterestResponse(GetClosestPointOfInterestResponse value) {
        return new JAXBElement<GetClosestPointOfInterestResponse>(_GetClosestPointOfInterestResponse_QNAME, GetClosestPointOfInterestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClosestPointOfInterest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.poi.nearby/", name = "getClosestPointOfInterest")
    public JAXBElement<GetClosestPointOfInterest> createGetClosestPointOfInterest(GetClosestPointOfInterest value) {
        return new JAXBElement<GetClosestPointOfInterest>(_GetClosestPointOfInterest_QNAME, GetClosestPointOfInterest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPointOfInterestsGreaterThanCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.poi.nearby/", name = "getPointOfInterestsGreaterThanCountResponse")
    public JAXBElement<GetPointOfInterestsGreaterThanCountResponse> createGetPointOfInterestsGreaterThanCountResponse(GetPointOfInterestsGreaterThanCountResponse value) {
        return new JAXBElement<GetPointOfInterestsGreaterThanCountResponse>(_GetPointOfInterestsGreaterThanCountResponse_QNAME, GetPointOfInterestsGreaterThanCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPointOfInterestsGreaterThanCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.poi.nearby/", name = "getPointOfInterestsGreaterThanCount")
    public JAXBElement<GetPointOfInterestsGreaterThanCount> createGetPointOfInterestsGreaterThanCount(GetPointOfInterestsGreaterThanCount value) {
        return new JAXBElement<GetPointOfInterestsGreaterThanCount>(_GetPointOfInterestsGreaterThanCount_QNAME, GetPointOfInterestsGreaterThanCount.class, null, value);
    }

}
