package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.AssetDTO;
import com.financialLab.financedevapp.dto.DebtDTO;
import com.financialLab.financedevapp.dto.RevenueDTO;
import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Asset;
import com.financialLab.financedevapp.models.Revenue;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.AssetService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/{assetExtenalId}")
    public ResponseEntity<ResponseDTO<AssetDTO>> getDebt(@PathVariable("assetExtenalId") String assetExtenalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(AssetDTO.of(assetService.getByExternalId(assetExtenalId))));

    }

    @PostMapping
    public ResponseEntity<ResponseDTO<AssetDTO>> create(@RequestBody AssetDTO assetDTO) {
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(AssetDTO.of(assetService.create(assetDTO, loggedUser))));
    }

    @PostMapping("/add-list")
    public ResponseEntity<ResponseDTO<List<AssetDTO>>> createAll(@RequestBody List<AssetDTO> assetDTOS) {
        User loggedUser = SecurityService.getLoggedUser();
        List<Asset> response = assetService.createAll(assetDTOS, loggedUser);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response.stream().map(AssetDTO::of).toList()));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<AssetDTO>> deleteAssetById(@PathVariable("externalId") String externalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(AssetDTO.of(assetService.deleteByExternalId(externalId))));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<AssetDTO>> updateAsset(@PathVariable("externalId") String externalId, @RequestBody AssetDTO dto){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Asset newEntity = AssetDTO.toModel(dto);
        Asset updatedEntity = assetService.updateByExternalId(externalId, newEntity);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(AssetDTO.of(updatedEntity)));
    }
}
