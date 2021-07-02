package mag.gaia.common.command;

import mag.gaia.common.services.dev.FixtureService;
import ninja.standalone.NinjaConsole;
import ninja.utils.NinjaMode;

public class FixtureCommand{
    static public void main(String[] args) throws Exception {
        NinjaConsole ninja = new NinjaConsole()
                .ninjaMode(NinjaMode.dev)
                .start();

        ninja.getInjector().getInstance(FixtureService.class).fixture();

        ninja.shutdown();
    }
}
