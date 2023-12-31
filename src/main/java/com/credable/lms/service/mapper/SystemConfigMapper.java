package com.credable.lms.service.mapper;


import com.credable.lms.domain.SystemConfig;
import com.credable.lms.service.dto.SystemConfigDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = { })
public interface SystemConfigMapper extends EntityMapper<SystemConfigDTO, SystemConfig> {

    SystemConfigDTO toDto(SystemConfig customer);

    SystemConfig toEntity(SystemConfigDTO systemConfigDTO);

    default SystemConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setId(id);
        return systemConfig;
    }
}
