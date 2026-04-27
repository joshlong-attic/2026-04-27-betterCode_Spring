package com.example.adoptions.dogs;

import org.springframework.data.annotation.Id;

record Dog(@Id int id, String name, String description, String owner) {
}
