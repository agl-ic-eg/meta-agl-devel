# remove the python3-llvmlite present in the meta-python-ai because it requires LLVM 15 to compile but AGL is using LLVM 18 rn
RDEPENDS:${PN}:remove = "python3-llvmlite"

# adding a precompile wheel for llvmlite-0.43.0 for x86_64 only
RDEPENDS:${PN} += "llvmlite"

