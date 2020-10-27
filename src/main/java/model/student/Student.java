package model.student;

public class Student {
    private int id, personalTutor, currYear, enrolYear;
    private String password, status, dormancyReason, firstName, middleName, surname, image, phoneNumber,
    currCourseCode, entryQual, emergContactPhone, emergContactEmail, employer, addNotes,
    medicalHistory, medicalAllergies, medicalReligious, termAddress, homeAddress, email,
    addTermNumber, addTermHouse, addTermTown, addTermCounty, addTermCountry, addTermZip,
    homeNumber, homeHouse, homeTown, homeCounty, homeCountry, homeZip, addTermStreet, homeStreet, gender;

    public Student() {

    }

    public Student(int id, int personalTutor, String password, String status, String dormancyReason, String firstName, String middleName, String surname, String image, String gender, String phoneNumber, String currCourseCode, int currYear, int enrolYear, String entryQual, String emergContactPhone, String emergContactEmail, String employer, String addNotes, String medicalHistory, String medicalAllergies, String medicalReligious,  String email, String addTermNumber,String addTermHouse, String addTermStreet, String addTermTown,String addTermCounty,String addTermCountry,String addTermZip,
                   String homeNumber, String homeHouse, String homeTown, String homeStreet, String homeCounty, String homeCountry, String homeZip) {
        this.id = id;
        this.personalTutor = personalTutor;
        this.password = password;
        this.status = status;
        this.dormancyReason = dormancyReason;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.image = image;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.currCourseCode = currCourseCode;
        this.currYear = currYear;
        this.enrolYear = enrolYear;
        this.entryQual = entryQual;
        this.emergContactPhone = emergContactPhone;
        this.emergContactEmail = emergContactEmail;
        this.employer = employer;
        this.addNotes = addNotes;
        this.medicalHistory = medicalHistory;
        this.medicalAllergies = medicalAllergies;
        this.medicalReligious = medicalReligious;
        this.addTermNumber = addTermNumber;
        this.addTermHouse = addTermHouse;
        this.addTermStreet = addTermStreet;
        this.addTermTown = addTermTown;
        this.addTermCounty = addTermCounty;
        this.addTermCountry = addTermCountry;
        this.homeNumber = homeNumber;
        this.homeHouse = homeHouse;
        this.homeStreet = homeStreet;
        this.homeTown = homeTown;
        this.homeCounty = homeCounty;
        this.homeCountry = homeCountry;
        this.homeZip = homeZip;
        this.addTermZip = addTermZip;
        this.homeNumber = homeNumber;
        this.homeHouse = homeHouse;
        this.homeTown = homeTown;
        this.homeCounty = homeCounty;
        this.homeCountry = homeCountry;
        this.homeZip = homeZip;
        this.email = email;
    }

    public String getAddTermStreet() {
        return addTermStreet;
    }

    public void setAddTermStreet(String addTermStreet) {
        this.addTermStreet = addTermStreet;
    }

    public String getHomeStreet() {
        return homeStreet;
    }

    public void setHomeStreet(String homeStreet) {
        this.homeStreet = homeStreet;
    }

    public String getAddTermNumber() {
        return addTermNumber;
    }

    public void setAddTermNumber(String addTermNumber) {
        this.addTermNumber = addTermNumber;
    }

    public String getAddTermHouse() {
        return addTermHouse;
    }

    public void setAddTermHouse(String addTermHouse) {
        this.addTermHouse = addTermHouse;
    }

    public String getAddTermTown() {
        return addTermTown;
    }

    public void setAddTermTown(String addTermTown) {
        this.addTermTown = addTermTown;
    }

    public String getAddTermCounty() {
        return addTermCounty;
    }

    public void setAddTermCounty(String addTermCounty) {
        this.addTermCounty = addTermCounty;
    }

    public String getAddTermCountry() {
        return addTermCountry;
    }

    public void setAddTermCountry(String addTermCountry) {
        this.addTermCountry = addTermCountry;
    }

    public String getAddTermZip() {
        return addTermZip;
    }

    public void setAddTermZip(String addTermZip) {
        this.addTermZip = addTermZip;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getHomeHouse() {
        return homeHouse;
    }

    public void setHomeHouse(String homeHouse) {
        this.homeHouse = homeHouse;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(String homeCounty) {
        this.homeCounty = homeCounty;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public String getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalTutor() {
        return personalTutor;
    }

    public void setPersonalTutor(int personalTutor) {
        this.personalTutor = personalTutor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDormancyReason() {
        return dormancyReason;
    }

    public void setDormancyReason(String dormancyReason) {
        this.dormancyReason = dormancyReason;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrCourseCode() {
        return currCourseCode;
    }

    public void setCurrCourseCode(String currCourseCode) {
        this.currCourseCode = currCourseCode;
    }

    public int getCurrYear() {
        return currYear;
    }

    public void setCurrYear(int currYear) {
        this.currYear = currYear;
    }

    public int getEnrolYear() {
        return enrolYear;
    }

    public void setEnrolYear(int enrolYear) {
        this.enrolYear = enrolYear;
    }

    public String getEntryQual() {
        return entryQual;
    }

    public void setEntryQual(String entryQual) {
        this.entryQual = entryQual;
    }

    public String getEmergContactPhone() {
        return emergContactPhone;
    }

    public void setEmergContactPhone(String emergContactPhone) {
        this.emergContactPhone = emergContactPhone;
    }

    public String getEmergContactEmail() {
        return emergContactEmail;
    }

    public void setEmergContactEmail(String emergContactEmail) {
        this.emergContactEmail = emergContactEmail;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getAddNotes() {
        return addNotes;
    }

    public void setAddNotes(String addNotes) {
        this.addNotes = addNotes;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalAllergies() {
        return medicalAllergies;
    }

    public void setMedicalAllergies(String medicalAllergies) {
        this.medicalAllergies = medicalAllergies;
    }

    public String getMedicalReligious() {
        return medicalReligious;
    }

    public void setMedicalReligious(String medicalReligious) {
        this.medicalReligious = medicalReligious;
    }

    public String getTermAddress() {
        return termAddress;
    }

    public void setTermAddress(String termAddress) {
        this.termAddress = termAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEmpty() {
        if (password.isEmpty()) return true;
        if (status.isEmpty()) return true;
        if (firstName.isEmpty()) return true;
        if (middleName.isEmpty()) return true;
        if (surname.isEmpty()) return true;
        if (gender.isEmpty()) return true;
        if (currCourseCode.isEmpty()) return true;
        if (phoneNumber.isEmpty()) return true;
        if (email.isEmpty()) return true;
        return false;
    }
}
