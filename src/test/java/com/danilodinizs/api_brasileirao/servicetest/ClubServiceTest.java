package com.danilodinizs.api_brasileirao.servicetest;

import com.danilodinizs.api_brasileirao.service.ClubService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {

    @InjectMocks
    ClubService service;

    @Mock
}
