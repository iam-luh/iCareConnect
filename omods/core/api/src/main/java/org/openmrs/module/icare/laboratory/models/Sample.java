package org.openmrs.module.icare.laboratory.models;

// Generated Oct 7, 2020 12:48:40 PM by Hibernate Tools 5.2.10.Final

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.openmrs.*;
import org.openmrs.module.icare.core.JSONConverter;
import org.openmrs.module.icare.core.utils.ChildIdOnlyDeserializer;
import org.openmrs.module.icare.core.utils.ChildIdOnlySerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * LbSample generated by hbm2java
 */
@Entity
@Table(name = "lb_sample")
public class Sample extends BaseOpenmrsData implements java.io.Serializable, JSONConverter {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sample_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	@JsonSerialize(using = ChildIdOnlySerializer.class)
	@JsonDeserialize(using = ChildIdOnlyDeserializer.class)
	private Visit visit;
	
	@Column(name = "label", length = 65535)
	private String label;
	
	@Column(name = "date_time")
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name = "concept_id")
	@JsonSerialize(using = ChildIdOnlySerializer.class)
	@JsonDeserialize(using = ChildIdOnlyDeserializer.class)
	private Concept concept;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.sample", cascade = { CascadeType.PERSIST })
	private List<SampleOrder> sampleOrders = new ArrayList<SampleOrder>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sample")
	private List<SampleStatus> sampleStatuses = new ArrayList<SampleStatus>(0);
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonSerialize(using = ChildIdOnlySerializer.class)
	@JsonDeserialize(using = ChildIdOnlyDeserializer.class)
	private Location location;
	
	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sampleOrder.id.sample")
	//	private List<TestAllocation> testAllocations = new ArrayList<TestAllocation>(0);
	
	public Sample() {
	}
	
	public static Sample fromMap(Map<String, Object> sampleMap) {
		Sample newSample = new Sample();
		newSample.setLabel((String) sampleMap.get("label"));
		
		Visit visit = new Visit();
		visit.setUuid(((Map) sampleMap.get("visit")).get("uuid").toString());
		newSample.setVisit(visit);
		
		//		Concept concept = new Concept();
		//		concept.setUuid(((Map) sampleMap.get("concept")).get("uuid").toString());
		//		newSample.setConcept(concept);
		return newSample;
	}
	
	public Map<String, Object> toMap() {
		HashMap<String, Object> sampleObject = (new HashMap<String, Object>());
		sampleObject.put("label", this.getLabel());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		if (this.getDateTime() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateTime()));
			sampleObject.put("created", this.getDateTime());
		} else if (this.getDateCreated() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateCreated()));
			
			sampleObject.put("created", this.getDateCreated());
		}
		
		if (this.getDateTime() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateTime()));
			sampleObject.put("dateCreated", this.getDateTime());
		} else if (this.getDateCreated() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateCreated()));
			
			sampleObject.put("dateCreated", this.getDateCreated());
		}
		
		if (this.getDateTime() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateTime()));
			sampleObject.put("dateTimeCreated", this.getDateTime());
		} else if (this.getDateCreated() != null) {
			//			sampleObject.put("created", sdf.format(this.getDateCreated()));
			
			sampleObject.put("dateTimeCreated", this.getDateCreated());
		}
		
		sampleObject.put("uuid", this.getUuid());
		
		Map<String, Object> creatorObject = new HashMap<String, Object>();
		
		if (this.getCreator() != null) {
			creatorObject.put("uuid", this.getCreator().getUuid());
			creatorObject.put("display", this.getCreator().getDisplayString());
		}
		
		Map<String, Object> locationObject = new HashMap<String, Object>();
		
		if (this.getLocation() != null) {
			locationObject.put("uuid", this.getLocation().getUuid());
		}
		
		sampleObject.put("location", locationObject);
		sampleObject.put("creator", creatorObject);
		
		sampleObject.put("voided", this.getVoided());
		HashMap<String, Object> visitObject = new HashMap<String, Object>();
		visitObject.put("uuid", this.getVisit().getUuid());
		visitObject.put("startDateTime", this.getVisit().getStartDatetime());
		visitObject.put("stopDateTime", this.getVisit().getStopDatetime());
		
		sampleObject.put("visit", visitObject);
		
		//
		//		HashMap<String, Object> patientObject = new HashMap<String, Object>();
		//		patientObject.put("MRN",this.getVisit().getPatient().getPatientIdentifier().getIdentifier());
		//		patientObject.put("name",this.getVisit().getPatient().getPerson().getGivenName().concat((" ").concat(this.getVisit().getPatient().getPerson().getGivenName())));
		//		sampleObject.put("patient", patientObject);
		
		HashMap<String, Object> conceptObject = new HashMap<String, Object>();
		conceptObject.put("uuid", this.getConcept().getUuid());
		
		sampleObject.put("concept", conceptObject);
		
		List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
		
		for (SampleOrder sampleOrder : this.getSampleOrders()) {
			orders.add(sampleOrder.toMap());
		}
		
		sampleObject.put("orders", orders);
		
		List<Map<String, Object>> sampleStatusesList = new ArrayList<Map<String, Object>>();
		
		for (SampleStatus sampleStatus : this.getSampleStatuses()) {
			
			sampleStatusesList.add(sampleStatus.toMap());
		}
		
		sampleObject.put("statuses", sampleStatusesList);
		
		//add sample patietient details
		HashMap<String, Object> patientObject = new HashMap<String, Object>();
		patientObject.put("uuid", this.getVisit().getPatient().getUuid());
		patientObject.put("allergy", this.getVisit().getPatient().getAllergyStatus());
		//		patientObject.put("dob", this.getVisit().getPatient().getPerson().getBirthDateTime());
		patientObject.put("dob", this.getVisit().getPatient().getPerson().getBirthdate());
		
		//		if (this.getVisit().getPatient().getBirthDateTime() == null) {
		//			if (this.getVisit().getPatient().getPerson().getBirthDateTime() == null) {
		//
		//			} else {
		//				patientObject.put("dob", this.getVisit().getPatient().getPerson().getBirthDateTime());
		//			}
		//
		//		} else {
		//			patientObject.put("dob", this.getVisit().getPatient().getBirthDateTime());
		//		}
		
		List<HashMap<String, Object>> patientIdentifiers = new ArrayList<HashMap<String, Object>>();
		for (PatientIdentifier patientIdentifier : this.getVisit().getPatient().getIdentifiers()) {
			
			HashMap<String, Object> patientIdentifierObject = new HashMap<String, Object>();
			patientIdentifierObject.put("id", patientIdentifier.getIdentifier());
			patientIdentifierObject.put("name", patientIdentifier.getIdentifierType().getName());
			
			patientIdentifiers.add(patientIdentifierObject);
		}
		
		PersonAttribute phoneAttribute = this.getVisit().getPatient().getPerson().getAttribute(51);
		
		if (phoneAttribute != null) {
			patientObject.put("phone", phoneAttribute.getValue());
		}
		patientObject.put("identifiers", patientIdentifiers);
		patientObject.put("age", this.getVisit().getPatient().getAge());
		patientObject.put("familyName", this.getVisit().getPatient().getPersonName().getFamilyName());
		patientObject.put("middleName", this.getVisit().getPatient().getPersonName().getMiddleName());
		patientObject.put("givenName", this.getVisit().getPatient().getPersonName().getGivenName());
		patientObject.put("gender", this.getVisit().getPatient().getGender());
		patientObject.put("uuid", this.getVisit().getPatient().getUuid());
		
		sampleObject.put("patient", patientObject);
		
		return sampleObject;
	}
	
	public Date getDateTime() {
		return this.dateTime;
	}
	
	public void setDateTime(Date timeStamp) {
		this.dateTime = timeStamp;
	}
	
	public Visit getVisit() {
		return this.visit;
	}
	
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Concept getConcept() {
		return this.concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<SampleOrder> getSampleOrders() {
		return sampleOrders;
	}
	
	public void setSampleOrders(List<SampleOrder> sampleOrders) {
		this.sampleOrders = sampleOrders;
	}
	
	public List<SampleStatus> getSampleStatuses() {
		return sampleStatuses;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setSampleStatuses(List<SampleStatus> sampleStatuses) {
		this.sampleStatuses = sampleStatuses;
	}
	
}
