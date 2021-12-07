package com.android.book2joy;

public class User
{
	private String email;
	private String name;
	private String phone;
	private String password;
	private char gender;

    public User(String name, String email, String password, String phone, char gender)
    {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }
}
