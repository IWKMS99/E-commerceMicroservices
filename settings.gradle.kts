rootProject.name = "E-commerceMicroservices"

include("infra:service-discovery")
include("infra:config-server")
include("infra:api-gateway")
include("services:user-service")
include("services:catalog-service")
include("libs:shared-library")