package hanghae7e6.prototype.util;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RunningPortController {

    private final Environment env;

    @GetMapping("/api/running-port")
    public String getRunningPort() {
        List <String> profiles = Arrays.asList(env.getActiveProfiles());
        List <String> testProfiles = Arrays.asList("test1", "test2");
        String defaultProfile = profiles.isEmpty()? "local" : profiles.get(0);

        return profiles.stream().filter(testProfiles::contains).findAny().orElse(defaultProfile);
    }
}
