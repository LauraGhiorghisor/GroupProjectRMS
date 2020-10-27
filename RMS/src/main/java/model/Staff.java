package model;

public class Staff {
    private int staffID;
    private String status, dorm, firstName, middleName, surname, gender, addressNumber, addressName, addressStreet,
    addressTown, addressCounty, addressCountry, zipCode, telephone, email, emergPhone, emergEmail, specialism, resume,
    addNotes, medicalHistory, medicalAllergy, medicalReligious, image;

    public Staff(int staffID, String status, String dorm, String firstName, String middleName, String surname, String gender, String addressNumber, String addressName, String addressStreet, String addressTown, String addressCounty, String addressCountry, String zipCode, String telephone, String email, String emergPhone, String emergEmail, String specialism, String resume, String addNotes, String medicalHistory, String medicalAllergy, String medicalReligious, String image) {
        this.staffID = staffID;
        this.status = status;
        this.dorm = dorm;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.gender = gender;
        this.addressNumber = addressNumber;
        this.addressName = addressName;
        this.addressStreet = addressStreet;
        this.addressTown = addressTown;
        this.addressCounty = addressCounty;
        this.addressCountry = addressCountry;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.email = email;
        this.emergPhone = emergPhone;
        this.emergEmail = emergEmail;
        this.specialism = specialism;
        this.resume = resume;
        this.addNotes = addNotes;
        this.medicalHistory = medicalHistory;
        this.medicalAllergy = medicalAllergy;
        this.medicalReligious = medicalReligious;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergPhone() {
        return emergPhone;
    }

    public void setEmergPhone(String emergPhone) {
        this.emergPhone = emergPhone;
    }

    public String getEmergEmail() {
        return emergEmail;
    }

    public void setEmergEmail(String emergEmail) {
        this.emergEmail = emergEmail;
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
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

    public String getMedicalAllergy() {
        return medicalAllergy;
    }

    public void setMedicalAllergy(String medicalAllergy) {
        this.medicalAllergy = medicalAllergy;
    }

    public String getMedicalReligious() {
        return medicalReligious;
    }

    public void setMedicalReligious(String medicalReligious) {
        this.medicalReligious = medicalReligious;
    }
}