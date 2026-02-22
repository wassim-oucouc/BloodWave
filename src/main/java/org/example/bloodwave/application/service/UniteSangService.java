package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.request.DonDTO;
import org.example.bloodwave.domain.entity.Don;
import org.example.bloodwave.domain.entity.UniteSang;

public interface UniteSangService {

    public UniteSang createUnite(Don don);
}
