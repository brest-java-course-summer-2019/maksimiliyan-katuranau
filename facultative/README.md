# Facultative Application
[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/maksimiliyan-katuranau.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/maksimiliyan-katuranau)
[![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/maksimiliyan-katuranau/badge.svg)](https://coveralls.io/github/brest-java-course-summer-2019/maksimiliyan-katuranau)

  Demo project for facultative management with two entities: “Course” and “Student”, related as one to many.
    
  ## Prerequisites
    
  jdk11  
  maven 3+  
  
  ## Installing  
  > mvn clean install  
  
  ## Running the tests  
  > mvn clean test  
  
  ### Server test
  
  For server test jetty plugin can be used
  
      mvn jetty:run 
      
  Open [http://localhost:8080](http://localhost:8080/hello)
