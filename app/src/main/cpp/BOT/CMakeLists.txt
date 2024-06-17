CMAKE_MINIMUM_REQUIRED(VERSION 3.16)

PROJECT(BOT VERSION 1.0 LANGUAGES CXX)


set(ENABLE_DEBUG OFF)
if(ENABLE_DEBUG)
    message("***** native cmake use Debug *****")
    set(CMAKE_BUILD_TYPE "Debug")
    set(CMAKE_CXX_FLAGS_DEBUG "-g")
else()
    message("***** native cmake use Release *****")
    set(CMAKE_BUILD_TYPE "Release")
    set(CMAKE_CXX_FLAGS_RELEASE "-O3")
endif()

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Set Build Type if not set
if(NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE Debug CACHE STRING "Choose the type of build." FORCE)
    set_property(CACHE CMAKE_BUILD_TYPE PROPERTY STRINGS "Debug" "Release"
            "MinSizeRel" "RelWithDebInfo")
endif()

# Collect all source files
file(GLOB_RECURSE SOURCES "${PROJECT_SOURCE_DIR}/src/*.cpp")

# Create library
add_library(${PROJECT_NAME} SHARED ${SOURCES})

# Set include directories
target_include_directories(${PROJECT_NAME} PUBLIC ${PROJECT_SOURCE_DIR}/include)


# Find and link Eigen3
include_directories(
        ../3rdpart
        ../3rdpart
        ${CMAKE_CURRENT_SOURCE_DIR}/../3rdpart/opencv/include
)

# Find and link OpenCV
add_library(lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../../jniLibs/${ANDROID_ABI}/libopencv_java4.so
     )

message("lib_opencv=${lib_opencv}")
target_link_libraries( # Specifies the target library.
        ${PROJECT_NAME}

        ${CMAKE_CURRENT_SOURCE_DIR}/../../jniLibs/${ANDROID_ABI}/libopencv_videostab.a
        lib_opencv

        )