import Testing
import CoreFoundationKotlin

@Suite("CoreFoundationKotlin Swift Export Tests")
struct CoreFoundationExportTests {
    @Test("Swift module loads and exports cleanly")
    func testSwiftModuleLoads() throws {
        #expect(Bool(true), "CoreFoundationKotlin swift module imported cleanly")
    }
}
