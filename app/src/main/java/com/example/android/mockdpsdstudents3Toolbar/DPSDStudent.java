package com.example.android.mockdpsdstudents3Toolbar;

import java.time.LocalDate;



public class DPSDStudent {


    private static int index = 1;

    private String name;
    private LocalDate birthDate;
    private int sex;
    private String email;

    private int yearOfEntry;
    private boolean allButDissertation;
    private boolean shortlisted;

    private int ID;

    public boolean isShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(boolean shortlisted) {
        this.shortlisted = shortlisted;
    }

    public DPSDStudent(String name, LocalDate birthDate, int sex,
                       int yearOfEntry, boolean allButDissertation) {

        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;

        this.yearOfEntry = yearOfEntry;
        this.allButDissertation = allButDissertation;

        this.shortlisted = false;
        formatEmail();
        index++;
        ID = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(int yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }

    public boolean isAllButDissertation() {
        return allButDissertation;
    }

    public void setAllButDissertation(boolean allButDissertation) {
        this.allButDissertation = allButDissertation;
    }

    public int getID() {
        return ID;
    }



    // helper methods

    private void formatEmail() {

        String email =
                "dpsd" +
                        this.yearOfEntry%100 +
                        String.format("%03d", index) +
                        "@aegean.gr";
        setEmail(email);
    }

    /**
     * hasty method to return appropriate drawable resource icon according to sex
     * @return int corresponding to drawable resource icon
     */
    public int chooseIcon() {
        if (this.sex == 1) {
            return R.drawable.man;
        }
        else {
            return R.drawable.woman;
        }
    }

}
