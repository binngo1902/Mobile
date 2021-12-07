package com.android.book2joy;

import androidx.annotation.NonNull;

public class User
{
	private String email, password, name, phone, nationality;
	private int age;

	public User(String email, String password, String name, String phone, int age, String nationality)
	{
		this.email = email;
		this.password = password;
		this.name = name;
	}


	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@NonNull
	@Override
	public String toString()
	{
		return "email: "+email+" - username:"+name;
	}
}
