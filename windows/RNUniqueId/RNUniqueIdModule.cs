using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Unique.Id.RNUniqueId
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNUniqueIdModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNUniqueIdModule"/>.
        /// </summary>
        internal RNUniqueIdModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNUniqueId";
            }
        }
    }
}
