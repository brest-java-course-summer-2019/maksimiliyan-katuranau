package com.epam.brest2019.courses.scanner;

import com.epam.brest2019.courses.menu.EnteredValue;

public interface InputReader {

    EnteredValue receiveValue(String message);

    EnteredValue parseInputValue(String inputValue);
}
