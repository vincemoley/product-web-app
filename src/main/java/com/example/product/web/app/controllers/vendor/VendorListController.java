package com.example.product.web.app.controllers.vendor;

import com.kleancierge.product.api.contract.FieldErrors;
import com.kleancierge.product.api.contract.Result;
import com.kleancierge.product.api.contract.vendor.IVendorListService;
import com.kleancierge.product.api.contract.vendor.VendorListService;
import com.kleancierge.product.api.model.vendor.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorListController {
    private IVendorListService listService;

    private Result result;

    public VendorListController(IVendorListService listService) { this.listService = listService; }

    @GetMapping(value = "/products/{vendorId}")
    public Result list(Pageable page) {
        listService.execute(page, new VendorListService.ServiceResponse() {
            @Override
            public void errors(FieldErrors fieldErrors) { result = Result.ERROR(fieldErrors); }

            @Override
            public void success(Page<Model> products) { result = Result.SUCCESS(products); }
        });

        return result;
    }
}
